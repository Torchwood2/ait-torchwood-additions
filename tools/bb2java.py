"""Blockbench modded_entity (.bbmodel) -> Java TexturedModelData code (Yarn), following Blockbench's
own Java entity exporter: x and y flipped, groups become ModelPartData with pivots relative to their
parent, rotated cubes get their own cube_rN child."""
import json, math, sys

def f(v):
    s = ('%.4f' % v).rstrip('0').rstrip('.')
    if s in ('-0', ''): s = '0'
    if '.' not in s: s += '.0'
    return s + 'F'

def load(path):
    d = json.load(open(path))
    els = {e['uuid']: e for e in d['elements']}
    groups = {g['uuid']: g for g in d.get('groups', [])}
    return d, els, groups

def convert(path, root_name=None):
    d, els, groups = load(path)
    tw, th = d['resolution']['width'], d['resolution']['height']
    lines = []
    counter = [0]
    used_names = {}

    def gname(n):
        n = n.replace(' ', '_').replace('-', '_')
        if n[0].isdigit(): n = '_' + n
        if n in used_names:
            used_names[n] += 1
            n = f'{n}{used_names[n]}'
        else:
            used_names[n] = 0
        return n

    def cube_code(c, ox, oy, oz):
        size = [c['to'][i] - c['from'][i] for i in range(3)]
        x = ox - c['to'][0]
        y = -c['from'][1] - size[1] + oy
        z = c['from'][2] - oz
        u, v = c.get('uv_offset', [0, 0])
        s = f'.uv({int(u)}, {int(v)})'
        if c.get('mirror_uv'): s += '.mirrored()'
        s += f'.cuboid({f(x)}, {f(y)}, {f(z)}, {f(size[0])}, {f(size[1])}, {f(size[2])}, new Dilation({f(c.get("inflate", 0))}))'
        if c.get('mirror_uv'): s += '.mirrored(false)'
        return s

    def rot_args(r):
        return f'{f(-math.radians(r[0]))}, {f(-math.radians(r[1]))}, {f(math.radians(r[2]))}'

    def group(node, parent_var, parent_origin):
        g = groups.get(node['uuid'], node)
        if g.get('export') is False: return
        name = gname(g['name'])
        o = g['origin']; r = g.get('rotation', [0, 0, 0])
        if parent_origin is None:
            px, py, pz = -o[0], 24 - o[1], o[2]
        else:
            px, py, pz = -(o[0] - parent_origin[0]), -(o[1] - parent_origin[1]), o[2] - parent_origin[2]
        cubes = [els[c] for c in node['children'] if isinstance(c, str) and c in els and els[c].get('export', True) is not False]
        plain = [c for c in cubes if not any(c.get('rotation', [0, 0, 0]))]
        rotated = [c for c in cubes if any(c.get('rotation', [0, 0, 0]))]
        builder = 'ModelPartBuilder.create()' + ''.join('\n\t\t' + cube_code(c, o[0], o[1], o[2]) for c in plain)
        if any(r):
            tf = f'ModelTransform.of({f(px)}, {f(py)}, {f(pz)}, {rot_args(r)})'
        else:
            tf = f'ModelTransform.pivot({f(px)}, {f(py)}, {f(pz)})'
        lines.append(f'\t\tModelPartData {name} = {parent_var}.addChild("{g["name"]}", {builder}, {tf});\n')
        # Rotated cubes sharing a pivot and rotation share one child part, as Blockbench exports them.
        bundles = {}
        for c in reversed(rotated):
            bundles.setdefault((tuple(c['origin']), tuple(c['rotation'])), []).append(c)
        for (co, rot), cs in bundles.items():
            counter[0] += 1
            cx, cy, cz = -(co[0] - o[0]), -(co[1] - o[1]), co[2] - o[2]
            body = ''.join(cube_code(c, co[0], co[1], co[2]) for c in cs)
            lines.append(f'\t\t{name}.addChild("cube_r{counter[0]}", ModelPartBuilder.create(){body}, '
                         f'ModelTransform.of({f(cx)}, {f(cy)}, {f(cz)}, {rot_args(list(rot))}));\n')
        for ch in node['children']:
            if not isinstance(ch, str): group(ch, name, o)

    top = [n for n in d['outliner'] if not isinstance(n, str)]
    loose = [n for n in d['outliner'] if isinstance(n, str)]
    if loose:
        # Root-level cubes: put them in a synthetic root group at the origin.
        top.insert(0, {'uuid': '__root', 'name': 'root_cubes', 'origin': [0, 0, 0], 'children': loose})
        groups['__root'] = top[0]
    if root_name:
        # Wrap everything in one named root at the origin, so the model has a single part to render.
        wrapper = {'uuid': '__wrap', 'name': root_name, 'origin': [0, 0, 0], 'children': top}
        groups['__wrap'] = wrapper
        group(wrapper, 'modelPartData', None)
    else:
        for n in top: group(n, 'modelPartData', None)
    body = ''.join(lines)
    return f'''\t\tModelData modelData = new ModelData();
\t\tModelPartData modelPartData = modelData.getRoot();
{body}\t\treturn TexturedModelData.of(modelData, {tw}, {th});
'''

if __name__ == '__main__':
    print(convert(sys.argv[1], sys.argv[2] if len(sys.argv) > 2 else None))
