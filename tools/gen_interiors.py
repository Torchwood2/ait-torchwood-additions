"""Builds two desktops as structure files, with a preview picture each:

- Torchwood Hub: a round, dark operations room — obsidian-quartz roundel walls, Torchwood pillars, a grate ring
  in the floor around a raised console, and lamps in the ceiling.
- Wilds Lodge: a timber hall of Torchwood — log pillars, plank floor, leaf-draped beams under a glass roof,
  chairs round the console.

Usage: python3 tools/gen_interiors.py [resources dir]
"""
import math, os, sys, json
import nbtlib
from nbtlib import Compound, List, Int, String, IntArray
from PIL import Image, ImageDraw

R = sys.argv[1] if len(sys.argv) > 1 else 'src/main/resources'
SIZE = 48
TARDIS = IntArray([1715457354, 1891518530, -1766410436, 3035242])  # placeholder; AIT relinks it on placement


class Build:
    def __init__(self):
        self.blocks = {}  # (x,y,z) -> (name, props, nbt)

    def set(self, x, y, z, name, props=None, nbt=None):
        if 0 <= x < SIZE and 0 <= y < SIZE and 0 <= z < SIZE:
            self.blocks[(x, y, z)] = (name, tuple(sorted((props or {}).items())), nbt)

    def fill(self, x0, y0, z0, x1, y1, z1, name, props=None):
        for x in range(min(x0, x1), max(x0, x1) + 1):
            for y in range(min(y0, y1), max(y0, y1) + 1):
                for z in range(min(z0, z1), max(z0, z1) + 1):
                    self.set(x, y, z, name, props)

    def save(self, path):
        palette, index, blocks = [], {}, []
        for pos, (name, props, nbt) in sorted(self.blocks.items()):
            key = (name, props)
            if key not in index:
                index[key] = len(palette)
                entry = Compound({'Name': String(name)})
                if props:
                    entry['Properties'] = Compound({k: String(v) for k, v in props})
                palette.append(entry)
            b = Compound({'pos': List[Int]([Int(c) for c in pos]), 'state': Int(index[key])})
            if nbt is not None:
                b['nbt'] = nbt
            blocks.append(b)
        root = Compound({'DataVersion': Int(3465), 'size': List[Int]([Int(SIZE)] * 3), 'palette': List[Compound](palette),
                         'blocks': List[Compound](blocks), 'entities': List[Compound]([])})
        nbtlib.File(root, gzipped=True).save(path)


def console(b, x, y, z, variant):
    b.set(x, y, z, 'ait:console', {'facing': 'north'}, Compound({'id': String('ait:console_block_entity_type'), 'tardis': TARDIS,
                                                                 'variant': String(variant), 'type': String('ait:console/hartnell')}))


def door(b, x, y, z, facing):
    b.set(x, y, z, 'ait:door_block', {'facing': facing, 'level': '0', 'waterlogged': 'false'},
          Compound({'id': String('ait:door_block_entity_type'), 'tardis': TARDIS}))
    b.set(x, y + 1, z, 'minecraft:air')


C = 24  # room centre (x and z)


def torchwood_hub():
    b = Build()
    r, floor, h = 13, 2, 9
    for x in range(C - r - 1, C + r + 2):
        for z in range(C - r - 1, C + r + 2):
            d = math.hypot(x - C, z - C)
            if d <= r + 0.5:
                b.set(x, floor - 1, z, 'minecraft:polished_deepslate')
                b.set(x, floor, z, 'minecraft:polished_blackstone_bricks' if d > 5.5 else 'ata:torchwood_planks')
                if 5.5 < d <= 6.5:
                    b.set(x, floor, z, 'ata:grate_block')
                    b.set(x, floor - 1, z, 'minecraft:sea_lantern')
                for y in range(floor + 1, floor + h):
                    b.set(x, y, z, 'minecraft:air')
                b.set(x, floor + h, z, 'ata:stripped_torchwood_wood' if (x + z) % 2 else 'minecraft:deepslate_tiles', {'axis': 'y'} if (x + z) % 2 else None)
                if d > 3 and (x - C) % 5 == 0 and (z - C) % 5 == 0:
                    b.set(x, floor + h, z, 'minecraft:shroomlight')
            elif d <= r + 1.5:
                for y in range(floor - 1, floor + h + 1):
                    b.set(x, y, z, 'ata:obsiquartz_roundel_half' if floor < y < floor + h else 'minecraft:polished_deepslate')
    for a in range(0, 360, 45):
        px, pz = round(C + (r - 0.5) * math.cos(math.radians(a))), round(C + (r - 0.5) * math.sin(math.radians(a)))
        for y in range(floor + 1, floor + h):
            b.set(px, y, pz, 'ata:torchwood_log', {'axis': 'y'})
    # Raised console dais.
    for x in range(C - 3, C + 4):
        for z in range(C - 3, C + 4):
            if math.hypot(x - C, z - C) <= 3.2:
                b.set(x, floor + 1, z, 'ata:torchwood_slab', {'type': 'bottom', 'waterlogged': 'false'})
    b.set(C, floor + 1, C, 'ata:torchwood_planks')
    console(b, C, floor + 2, C, 'ait:console/hartnell_wooden')
    door(b, C + r + 1, floor + 1, C, 'west')
    for y in (floor + 1, floor + 2):
        b.set(C + r + 1, y, C - 1, 'ata:torchwood_log', {'axis': 'y'})
        b.set(C + r + 1, y, C + 1, 'ata:torchwood_log', {'axis': 'y'})
    for (cx, cz) in [(C - 8, C - 8), (C - 8, C + 8), (C + 8, C - 8)]:
        b.set(cx, floor + 1, cz, 'ata:torchwood_chair', {'facing': 'south' if cz < C else 'north'})
    return b


def wilds_lodge():
    b = Build()
    x0, x1, z0, z1, floor, h = C - 12, C + 12, C - 9, C + 9, 2, 10
    for x in range(x0, x1 + 1):
        for z in range(z0, z1 + 1):
            edge = x in (x0, x1) or z in (z0, z1)
            b.set(x, floor - 1, z, 'minecraft:stone_bricks')
            b.set(x, floor, z, 'ata:torchwood_planks' if not edge else 'ata:stripped_torchwood_wood', None if not edge else {'axis': 'y'})
            for y in range(floor + 1, floor + h):
                if edge:
                    pillar = (x - x0) % 6 == 0 and (z - z0) % 6 == 0 or (x in (x0, x1) and z in (z0, z1))
                    if pillar:
                        b.set(x, y, z, 'ata:torchwood_log', {'axis': 'y'})
                    elif y in (floor + 3, floor + 4, floor + 5) and not ((x - x0) % 6 in (0,) or (z - z0) % 6 in (0,)):
                        b.set(x, y, z, 'minecraft:glass_pane' if False else 'minecraft:glass')
                    else:
                        b.set(x, y, z, 'ata:torchwood_planks')
                else:
                    b.set(x, y, z, 'minecraft:air')
            b.set(x, floor + h, z, 'minecraft:glass' if not edge and abs(x - C) < 9 and abs(z - C) < 6 else 'ata:stripped_torchwood_wood', None if (not edge and abs(x - C) < 9 and abs(z - C) < 6) else {'axis': 'x'})
    # Beams under the glass, with leaves hanging off them.
    for x in range(x0 + 1, x1):
        if (x - x0) % 6 == 0:
            for z in range(z0 + 1, z1):
                b.set(x, floor + h - 1, z, 'ata:torchwood_log', {'axis': 'z'})
                if z % 3 == 0:
                    b.set(x, floor + h - 2, z, 'ata:torchwood_leaves', {'distance': '1', 'persistent': 'true', 'waterlogged': 'false'})
    # Lanterns, a rug, the console and chairs round it.
    for (lx, lz) in [(C - 6, C - 4), (C + 6, C - 4), (C - 6, C + 4), (C + 6, C + 4)]:
        b.set(lx, floor + h - 2, lz, 'minecraft:lantern', {'hanging': 'true', 'waterlogged': 'false'})
    for x in range(C - 3, C + 4):
        for z in range(C - 3, C + 4):
            b.set(x, floor + 1, z, 'minecraft:moss_carpet')
    console(b, C, floor + 1, C, 'ait:console/hartnell_wooden')
    for (cx, cz, f) in [(C, C - 3, 'south'), (C, C + 3, 'north'), (C - 3, C, 'east')]:
        b.set(cx, floor + 1, cz, 'ata:torchwood_chair', {'facing': f})
    b.set(x1, floor + 1, C, 'minecraft:air')
    door(b, x1, floor + 1, C, 'west')
    for (px, pz) in [(x0 + 2, z0 + 2), (x1 - 2, z1 - 2), (x0 + 2, z1 - 2)]:
        b.set(px, floor + 1, pz, 'minecraft:potted_fern')
    return b


# ---- preview: a simple isometric drawing coloured by block ----
COLOURS = {'torchwood_planks': (122, 50, 36), 'torchwood_log': (60, 32, 26), 'stripped_torchwood_wood': (140, 64, 44),
           'torchwood_slab': (122, 50, 36), 'torchwood_chair': (150, 70, 50), 'torchwood_leaves': (190, 70, 30),
           'obsiquartz_roundel_half': (40, 40, 48), 'polished_deepslate': (70, 70, 76), 'polished_blackstone_bricks': (48, 42, 50),
           'grate_block': (110, 110, 118), 'sea_lantern': (170, 220, 220), 'shroomlight': (240, 150, 80), 'deepslate_tiles': (56, 56, 62),
           'console': (60, 200, 220), 'door_block': (30, 60, 140), 'glass': (180, 220, 235), 'stone_bricks': (120, 120, 120),
           'moss_carpet': (80, 120, 40), 'lantern': (240, 200, 100), 'potted_fern': (60, 120, 50)}


def preview(b, path, cut):
    img = Image.new('RGB', (256, 256), (16, 18, 24))
    d = ImageDraw.Draw(img)
    s = 5.6
    vox = [(p, v[0].split(':')[1]) for p, v in b.blocks.items() if v[0] != 'minecraft:air' and p[1] < cut]
    vox.sort(key=lambda pv: (pv[0][0] + pv[0][2], pv[0][1]))
    for (x, y, z), name in vox:
        col = COLOURS.get(name, (150, 150, 150))
        sx = 128 + (x - z) * s * 0.87
        sy = 150 + (x + z - 2 * C) * s * 0.5 - (y - 4) * s
        top = [(sx, sy - s), (sx + s * 0.87, sy - s * 0.5), (sx, sy), (sx - s * 0.87, sy - s * 0.5)]
        d.polygon(top, fill=col)
        d.polygon([(sx - s * 0.87, sy - s * 0.5), (sx, sy), (sx, sy + s), (sx - s * 0.87, sy + s * 0.5)], fill=tuple(int(c * 0.7) for c in col))
        d.polygon([(sx + s * 0.87, sy - s * 0.5), (sx, sy), (sx, sy + s), (sx + s * 0.87, sy + s * 0.5)], fill=tuple(int(c * 0.55) for c in col))
    img.save(path)


os.makedirs(R + '/data/ata/structures/interiors', exist_ok=True)
lang = json.load(open(R + '/assets/ata/lang/en_us.json'))
for name, title, fn, cut in [('torchwood_hub', 'Torchwood Hub', torchwood_hub, 10), ('wilds_lodge', 'Wilds Lodge', wilds_lodge, 11)]:
    b = fn()
    b.save(f'{R}/data/ata/structures/interiors/{name}.nbt')
    preview(b, f'{R}/assets/ata/textures/desktop/{name}.png', cut)
    json.dump({'id': f'ata:{name}'}, open(f'{R}/data/ata/desktop/{name}.json', 'w'), indent=2)
    lang[f'desktop.ata.{name}'] = title
json.dump(lang, open(R + '/assets/ata/lang/en_us.json', 'w'), indent=2, ensure_ascii=False)
print('interiors done')
