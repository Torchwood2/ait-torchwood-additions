"""Generates the JSON for the Torchwood wood set, Rift gear, chairs, guns and the Wilds dimension.

Usage: python3 tools/gen_content.py <unzipped vanilla 1.20.1 jar> [resources dir]
The vanilla oak/diamond files are used as templates (renamed to Torchwood/Rift), so the blockstates, models,
loot and recipes match vanilla's exactly.
"""
import json, os, re, sys, glob

MC = sys.argv[1]
R = sys.argv[2] if len(sys.argv) > 2 else 'src/main/resources'
A, D = R + '/assets/ata', R + '/data/ata'


def w(path, obj):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w') as f:
        json.dump(obj, f, indent=2)


def rd(path):
    return json.load(open(path))


lang = {}

# ---------------------------------------------------------------- Torchwood
WOOD_PARTS = ['log', 'wood', 'stripped_log', 'stripped_wood', 'planks', 'leaves', 'sapling', 'stairs', 'slab', 'fence',
              'fence_gate', 'door', 'trapdoor', 'button', 'pressure_plate']


def oak_to_tw(s):
    s = s.replace('minecraft:block/stripped_oak', 'ata:block/stripped_torchwood')
    s = s.replace('minecraft:block/oak', 'ata:block/torchwood')
    s = s.replace('minecraft:item/oak', 'ata:item/torchwood')
    s = s.replace('minecraft:stripped_oak', 'ata:stripped_torchwood').replace('minecraft:oak', 'ata:torchwood')
    s = s.replace('minecraft:potted_oak', 'ata:potted_torchwood')
    s = s.replace('#minecraft:oak_logs', '#ata:torchwood_logs')
    return s


def vanilla_name(part):
    return 'stripped_oak_' + part[9:] if part.startswith('stripped_') else 'oak_' + part


def tw_name(part):
    return 'stripped_torchwood_' + part[9:] if part.startswith('stripped_') else 'torchwood_' + part


for part in WOOD_PARTS + ['potted_sapling']:
    v = 'potted_oak_sapling' if part == 'potted_sapling' else vanilla_name(part)
    t = 'potted_torchwood_sapling' if part == 'potted_sapling' else tw_name(part)
    for src, dst in [(f'{MC}/assets/minecraft/blockstates/{v}.json', f'{A}/blockstates/{t}.json'),
                     (f'{MC}/data/minecraft/loot_tables/blocks/{v}.json', f'{D}/loot_tables/blocks/{t}.json')]:
        w(dst, json.loads(oak_to_tw(open(src).read())))
    for src in glob.glob(f'{MC}/assets/minecraft/models/block/{v}*.json'):
        name = os.path.basename(src)
        if v == 'oak_log' and not (name.startswith('oak_log.') or name.startswith('oak_log_horizontal')):
            continue
        w(f'{A}/models/block/' + name.replace(v, t, 1), json.loads(oak_to_tw(open(src).read())))
    if part != 'potted_sapling':
        src = f'{MC}/assets/minecraft/models/item/{v}.json'
        w(f'{A}/models/item/{t}.json', json.loads(oak_to_tw(open(src).read())))
        r = f'{MC}/data/minecraft/recipes/{v}.json'
        if os.path.exists(r):
            j = json.loads(oak_to_tw(open(r).read()))
            j.pop('group', None)
            w(f'{D}/recipes/{t}.json', j)
# The wood models point at the log textures for their sides.
for t in ['torchwood_wood', 'stripped_torchwood_wood']:
    p = f'{A}/models/block/{t}.json'
    m = rd(p)
    side = 'ata:block/' + t.replace('_wood', '_log')
    m['textures'] = {'all': side} if 'all' in m.get('textures', {}) else {'end': side, 'side': side}
    w(p, m)
# Leaves keep their own colour rather than the biome's foliage tint.
w(f'{A}/models/block/torchwood_leaves.json', {'parent': 'minecraft:block/cube_all', 'textures': {'all': 'ata:block/torchwood_leaves'}})

lang.update({
    'block.ata.torchwood_log': 'Torchwood Log', 'block.ata.torchwood_wood': 'Torchwood Wood',
    'block.ata.stripped_torchwood_log': 'Stripped Torchwood Log', 'block.ata.stripped_torchwood_wood': 'Stripped Torchwood Wood',
    'block.ata.torchwood_planks': 'Torchwood Planks', 'block.ata.torchwood_leaves': 'Torchwood Leaves',
    'block.ata.torchwood_sapling': 'Torchwood Sapling', 'block.ata.potted_torchwood_sapling': 'Potted Torchwood Sapling',
    'block.ata.torchwood_stairs': 'Torchwood Stairs', 'block.ata.torchwood_slab': 'Torchwood Slab',
    'block.ata.torchwood_fence': 'Torchwood Fence', 'block.ata.torchwood_fence_gate': 'Torchwood Fence Gate',
    'block.ata.torchwood_door': 'Torchwood Door', 'block.ata.torchwood_trapdoor': 'Torchwood Trapdoor',
    'block.ata.torchwood_button': 'Torchwood Button', 'block.ata.torchwood_pressure_plate': 'Torchwood Pressure Plate',
})

TW_LOGS = ['ata:torchwood_log', 'ata:torchwood_wood', 'ata:stripped_torchwood_log', 'ata:stripped_torchwood_wood']
tags = {  # (registry, tag) -> values ; block and item tags
    ('blocks', 'ata:torchwood_logs'): TW_LOGS, ('items', 'ata:torchwood_logs'): TW_LOGS,
    ('blocks', 'minecraft:logs_that_burn'): ['#ata:torchwood_logs'], ('items', 'minecraft:logs_that_burn'): ['#ata:torchwood_logs'],
    ('blocks', 'minecraft:overworld_natural_logs'): ['ata:torchwood_log'], ('items', 'minecraft:overworld_natural_logs'): ['ata:torchwood_log'],
    ('blocks', 'minecraft:planks'): ['ata:torchwood_planks'], ('items', 'minecraft:planks'): ['ata:torchwood_planks'],
    ('blocks', 'minecraft:wooden_stairs'): ['ata:torchwood_stairs'], ('items', 'minecraft:wooden_stairs'): ['ata:torchwood_stairs'],
    ('blocks', 'minecraft:wooden_slabs'): ['ata:torchwood_slab'], ('items', 'minecraft:wooden_slabs'): ['ata:torchwood_slab'],
    ('blocks', 'minecraft:wooden_fences'): ['ata:torchwood_fence'], ('items', 'minecraft:wooden_fences'): ['ata:torchwood_fence'],
    ('blocks', 'minecraft:fence_gates'): ['ata:torchwood_fence_gate'], ('items', 'minecraft:fence_gates'): ['ata:torchwood_fence_gate'],
    ('blocks', 'minecraft:wooden_doors'): ['ata:torchwood_door'], ('items', 'minecraft:wooden_doors'): ['ata:torchwood_door'],
    ('blocks', 'minecraft:wooden_trapdoors'): ['ata:torchwood_trapdoor'], ('items', 'minecraft:wooden_trapdoors'): ['ata:torchwood_trapdoor'],
    ('blocks', 'minecraft:wooden_buttons'): ['ata:torchwood_button'], ('items', 'minecraft:wooden_buttons'): ['ata:torchwood_button'],
    ('blocks', 'minecraft:wooden_pressure_plates'): ['ata:torchwood_pressure_plate'], ('items', 'minecraft:wooden_pressure_plates'): ['ata:torchwood_pressure_plate'],
    ('blocks', 'minecraft:leaves'): ['ata:torchwood_leaves'], ('items', 'minecraft:leaves'): ['ata:torchwood_leaves'],
    ('blocks', 'minecraft:saplings'): ['ata:torchwood_sapling'], ('items', 'minecraft:saplings'): ['ata:torchwood_sapling'],
    ('blocks', 'minecraft:flower_pots'): ['ata:potted_torchwood_sapling'],
}

# ---------------------------------------------------------------- Rift
for b in ['rift_ore', 'deepslate_rift_ore', 'rift_block']:
    w(f'{A}/blockstates/{b}.json', {'variants': {'': {'model': f'ata:block/{b}'}}})
    w(f'{A}/models/block/{b}.json', {'parent': 'minecraft:block/cube_all', 'textures': {'all': f'ata:block/{b}'}})
    w(f'{A}/models/item/{b}.json', {'parent': f'ata:block/{b}'})
ore_loot = open(f'{MC}/data/minecraft/loot_tables/blocks/diamond_ore.json').read()
for b in ['rift_ore', 'deepslate_rift_ore']:
    w(f'{D}/loot_tables/blocks/{b}.json', json.loads(ore_loot.replace('minecraft:diamond_ore', f'ata:{b}').replace('minecraft:diamond', 'ata:rift_shard')))
w(f'{D}/loot_tables/blocks/rift_block.json', {'type': 'minecraft:block', 'pools': [{'rolls': 1, 'bonus_rolls': 0, 'entries': [{'type': 'minecraft:item', 'name': 'ata:rift_block'}], 'conditions': [{'condition': 'minecraft:survives_explosion'}]}]})

for it in ['rift_shard', 'rift_helmet', 'rift_chestplate', 'rift_leggings', 'rift_boots']:
    w(f'{A}/models/item/{it}.json', {'parent': 'minecraft:item/generated', 'textures': {'layer0': f'ata:item/{it}'}})
for it in ['rift_sword', 'rift_pickaxe', 'rift_axe', 'rift_shovel', 'rift_hoe']:
    w(f'{A}/models/item/{it}.json', {'parent': 'minecraft:item/handheld', 'textures': {'layer0': f'ata:item/{it}'}})


def diamond_to_rift(s):
    s = s.replace('"minecraft:diamond_block"', '"ata:rift_block"')
    s = re.sub(r'"minecraft:diamond_(sword|pickaxe|axe|shovel|hoe|helmet|chestplate|leggings|boots)"', r'"ata:rift_\1"', s)
    s = s.replace('"minecraft:diamond"', '"ata:rift_shard"').replace('"minecraft:diamond_ore"', '"ata:rift_ore"')
    s = s.replace('"minecraft:deepslate_diamond_ore"', '"ata:deepslate_rift_ore"')
    return s


for kind in ['sword', 'pickaxe', 'axe', 'shovel', 'hoe', 'helmet', 'chestplate', 'leggings', 'boots', 'block']:
    j = json.loads(diamond_to_rift(open(f'{MC}/data/minecraft/recipes/diamond_{kind}.json').read()))
    j.pop('group', None)
    w(f'{D}/recipes/rift_{kind}.json', j)
w(f'{D}/recipes/rift_shard_from_block.json', {'type': 'minecraft:crafting_shapeless', 'ingredients': [{'item': 'ata:rift_block'}], 'result': {'item': 'ata:rift_shard', 'count': 9}})
for ore in ['rift_ore', 'deepslate_rift_ore']:
    for kind, time in [('smelting', 200), ('blasting', 100)]:
        w(f'{D}/recipes/rift_shard_from_{kind}_{ore}.json', {'type': f'minecraft:{kind}', 'ingredient': {'item': f'ata:{ore}'}, 'result': 'ata:rift_shard', 'experience': 1.0, 'cookingtime': time})

lang.update({'block.ata.rift_ore': 'Rift Ore', 'block.ata.deepslate_rift_ore': 'Deepslate Rift Ore', 'block.ata.rift_block': 'Block of Rift',
             'item.ata.rift_shard': 'Rift Shard', 'item.ata.rift_sword': 'Rift Sword', 'item.ata.rift_pickaxe': 'Rift Pickaxe',
             'item.ata.rift_axe': 'Rift Axe', 'item.ata.rift_shovel': 'Rift Shovel', 'item.ata.rift_hoe': 'Rift Hoe',
             'item.ata.rift_helmet': 'Rift Helmet', 'item.ata.rift_chestplate': 'Rift Chestplate',
             'item.ata.rift_leggings': 'Rift Leggings', 'item.ata.rift_boots': 'Rift Boots'})
tags.update({
    ('blocks', 'minecraft:mineable/pickaxe'): ['ata:rift_ore', 'ata:deepslate_rift_ore', 'ata:rift_block'],
    ('blocks', 'minecraft:needs_diamond_tool'): ['ata:rift_ore', 'ata:deepslate_rift_ore'],
    ('blocks', 'minecraft:needs_iron_tool'): ['ata:rift_block'],
    ('blocks', 'minecraft:beacon_base_blocks'): ['ata:rift_block'], ('items', 'minecraft:beacon_payment_items'): ['ata:rift_shard'],
    ('items', 'minecraft:trim_materials'): [], ('items', 'minecraft:swords'): ['ata:rift_sword'],
    ('items', 'minecraft:pickaxes'): ['ata:rift_pickaxe'], ('items', 'minecraft:axes'): ['ata:rift_axe'],
    ('items', 'minecraft:shovels'): ['ata:rift_shovel'], ('items', 'minecraft:hoes'): ['ata:rift_hoe'],
    ('items', 'minecraft:trimmable_armor'): ['ata:rift_helmet', 'ata:rift_chestplate', 'ata:rift_leggings', 'ata:rift_boots'],
    ('blocks', 'c:ores'): ['ata:rift_ore', 'ata:deepslate_rift_ore'], ('items', 'c:ores'): ['ata:rift_ore', 'ata:deepslate_rift_ore'],
})
tags.pop(('items', 'minecraft:trim_materials'))

# ---------------------------------------------------------------- Chairs
CHAIR_WOODS = ['oak', 'spruce', 'birch', 'jungle', 'acacia', 'dark_oak', 'mangrove', 'cherry', 'bamboo', 'crimson', 'warped', 'torchwood']


def cube(frm, to, tex='#planks'):
    faces = {}
    for f in ['north', 'south', 'east', 'west', 'up', 'down']:
        faces[f] = {'texture': tex}
    return {'from': frm, 'to': to, 'faces': faces}


# Faces north (the sitter looks north), so the back is on the south side.
w(f'{A}/models/block/chair_template.json', {
    'parent': 'minecraft:block/block', 'textures': {'particle': '#planks'},
    'elements': [cube([2, 0, 2], [4, 7, 4]), cube([12, 0, 2], [14, 7, 4]), cube([2, 0, 12], [4, 7, 14]), cube([12, 0, 12], [14, 7, 14]),
                 cube([2, 7, 2], [14, 9, 14]),
                 cube([2, 9, 12], [4, 22, 14]), cube([12, 9, 12], [14, 22, 14]), cube([4, 15, 12.5], [12, 21, 13.5]),
                 cube([4, 11, 12.5], [12, 13, 13.5])],
    'display': {'gui': {'rotation': [30, 225, 0], 'scale': [0.55, 0.55, 0.55], 'translation': [0, -1.5, 0]},
                'ground': {'scale': [0.25, 0.25, 0.25], 'translation': [0, 3, 0]},
                'fixed': {'scale': [0.5, 0.5, 0.5]},
                'thirdperson_righthand': {'rotation': [75, 45, 0], 'translation': [0, 0.5, 1], 'scale': [0.3, 0.3, 0.3]},
                'firstperson_righthand': {'rotation': [0, 45, 0], 'scale': [0.35, 0.35, 0.35]},
                'firstperson_lefthand': {'rotation': [0, 225, 0], 'scale': [0.35, 0.35, 0.35]}}})
for wood in CHAIR_WOODS:
    planks = 'ata:block/torchwood_planks' if wood == 'torchwood' else (
        'minecraft:block/bamboo_planks' if wood == 'bamboo' else f'minecraft:block/{wood}_planks')
    name = f'{wood}_chair'
    w(f'{A}/models/block/{name}.json', {'parent': 'ata:block/chair_template', 'textures': {'planks': planks}})
    w(f'{A}/models/item/{name}.json', {'parent': f'ata:block/{name}'})
    w(f'{A}/blockstates/{name}.json', {'variants': {f'facing={f}': ({'model': f'ata:block/{name}', 'y': y} if y else {'model': f'ata:block/{name}'})
                                                    for f, y in [('north', 0), ('east', 90), ('south', 180), ('west', 270)]}})
    w(f'{D}/loot_tables/blocks/{name}.json', {'type': 'minecraft:block', 'pools': [{'rolls': 1, 'bonus_rolls': 0, 'entries': [{'type': 'minecraft:item', 'name': f'ata:{name}'}], 'conditions': [{'condition': 'minecraft:survives_explosion'}]}]})
    plank_item = 'ata:torchwood_planks' if wood == 'torchwood' else f'minecraft:{wood}_planks'
    w(f'{D}/recipes/{name}.json', {'type': 'minecraft:crafting_shaped', 'category': 'building', 'pattern': ['P  ', 'PPP', 'S S'],
                                    'key': {'P': {'item': plank_item}, 'S': {'item': 'minecraft:stick'}}, 'result': {'item': f'ata:{name}', 'count': 2}})
    lang[f'block.ata.{name}'] = wood.replace('_', ' ').title() + ' Chair'
tags[('blocks', 'minecraft:mineable/axe')] = [f'ata:{wd}_chair' for wd in CHAIR_WOODS]
lang['entity.ata.seat'] = 'Seat'

# ---------------------------------------------------------------- Guns
for g in ['torchwood_service_pistol', 'rift_carbine', 'rift_lance']:
    w(f'{A}/models/item/{g}.json', {'parent': 'minecraft:item/handheld', 'textures': {'layer0': f'ata:item/{g}'},
                                    'display': {'thirdperson_righthand': {'rotation': [0, -90, 0], 'translation': [0, 3, 1], 'scale': [0.85, 0.85, 0.85]},
                                                'thirdperson_lefthand': {'rotation': [0, 90, 0], 'translation': [0, 3, 1], 'scale': [0.85, 0.85, 0.85]},
                                                'firstperson_righthand': {'rotation': [0, -90, 0], 'translation': [1.13, 3.2, 1.13], 'scale': [0.68, 0.68, 0.68]},
                                                'firstperson_lefthand': {'rotation': [0, 90, 0], 'translation': [1.13, 3.2, 1.13], 'scale': [0.68, 0.68, 0.68]}}})
w(f'{D}/recipes/torchwood_service_pistol.json', {'type': 'minecraft:crafting_shaped', 'category': 'equipment', 'pattern': ['III', ' EP', '  P'],
                                                  'key': {'I': {'item': 'minecraft:iron_ingot'}, 'E': {'tag': 'c:energized_atrium'}, 'P': {'item': 'ata:torchwood_planks'}},
                                                  'result': {'item': 'ata:torchwood_service_pistol'}})
w(f'{D}/recipes/rift_carbine.json', {'type': 'minecraft:crafting_shaped', 'category': 'equipment', 'pattern': ['IRR', 'RCI', ' PP'],
                                      'key': {'I': {'item': 'minecraft:iron_ingot'}, 'R': {'item': 'ata:rift_shard'}, 'C': {'tag': 'c:atrium_cores'}, 'P': {'item': 'ata:torchwood_planks'}},
                                      'result': {'item': 'ata:rift_carbine'}})
w(f'{D}/recipes/rift_lance.json', {'type': 'minecraft:crafting_shaped', 'category': 'equipment', 'pattern': [' RS', 'RCR', 'BR '],
                                    'key': {'R': {'item': 'ata:rift_shard'}, 'S': {'item': 'minecraft:spyglass'}, 'C': {'tag': 'c:atrium_cores'}, 'B': {'item': 'ata:rift_block'}},
                                    'result': {'item': 'ata:rift_lance'}})
lang.update({'item.ata.torchwood_service_pistol': 'Torchwood Service Pistol', 'item.ata.rift_carbine': 'Rift Carbine',
             'item.ata.rift_lance': 'Rift Lance', 'tooltip.ata.gun.stats': '%s shots/s, %s damage'})

# ---------------------------------------------------------------- The Wilds
w(f'{D}/worldgen/configured_feature/torchwood_tree.json', {'type': 'minecraft:tree', 'config': {
    'ignore_vines': True, 'force_dirt': False,
    'dirt_provider': {'type': 'minecraft:simple_state_provider', 'state': {'Name': 'minecraft:dirt'}},
    'trunk_provider': {'type': 'minecraft:simple_state_provider', 'state': {'Name': 'ata:torchwood_log', 'Properties': {'axis': 'y'}}},
    'trunk_placer': {'type': 'minecraft:fancy_trunk_placer', 'base_height': 8, 'height_rand_a': 5, 'height_rand_b': 2},
    'foliage_provider': {'type': 'minecraft:simple_state_provider', 'state': {'Name': 'ata:torchwood_leaves', 'Properties': {'distance': '7', 'persistent': 'false', 'waterlogged': 'false'}}},
    'foliage_placer': {'type': 'minecraft:fancy_foliage_placer', 'radius': 2, 'offset': 4, 'height': 4},
    'minimum_size': {'type': 'minecraft:two_layers_feature_size', 'limit': 0, 'lower_size': 0, 'upper_size': 0, 'min_clipped_height': 4},
    'decorators': [{'type': 'minecraft:trunk_vine'}, {'type': 'minecraft:leave_vine', 'probability': 0.25}]}})
w(f'{D}/worldgen/placed_feature/torchwood_trees.json', {'feature': 'ata:torchwood_tree', 'placement': [
    {'type': 'minecraft:count', 'count': {'type': 'minecraft:weighted_list', 'distribution': [{'weight': 9, 'data': 1}, {'weight': 1, 'data': 3}]}},
    {'type': 'minecraft:in_square'}, {'type': 'minecraft:surface_water_depth_filter', 'max_water_depth': 0},
    {'type': 'minecraft:heightmap', 'heightmap': 'OCEAN_FLOOR'},
    {'type': 'minecraft:block_predicate_filter', 'predicate': {'type': 'minecraft:would_survive', 'state': {'Name': 'ata:torchwood_sapling', 'Properties': {'stage': '0'}}}},
    {'type': 'minecraft:biome'}]})
w(f'{D}/worldgen/configured_feature/ore_rift.json', {'type': 'minecraft:ore', 'config': {'size': 5, 'discard_chance_on_air_exposure': 0.3, 'targets': [
    {'target': {'predicate_type': 'minecraft:tag_match', 'tag': 'minecraft:stone_ore_replaceables'}, 'state': {'Name': 'ata:rift_ore'}},
    {'target': {'predicate_type': 'minecraft:tag_match', 'tag': 'minecraft:deepslate_ore_replaceables'}, 'state': {'Name': 'ata:deepslate_rift_ore'}}]}})
w(f'{D}/worldgen/placed_feature/ore_rift.json', {'feature': 'ata:ore_rift', 'placement': [
    {'type': 'minecraft:count', 'count': 6}, {'type': 'minecraft:in_square'},
    {'type': 'minecraft:height_range', 'height': {'type': 'minecraft:trapezoid', 'min_inclusive': {'above_bottom': -16}, 'max_inclusive': {'absolute': 48}}},
    {'type': 'minecraft:biome'}]})

BIOMES = {'wild_jungle': ('jungle', 'Wild Jungle'), 'great_desert': ('desert', 'Great Desert'), 'the_mire': ('mangrove_swamp', 'The Mire')}
for name, (base, title) in BIOMES.items():
    b = rd(f'{MC}/data/minecraft/worldgen/biome/{base}.json')
    feats = [list(step) for step in b['features']]
    while len(feats) < 11:
        feats.append([])
    feats[6].append('ata:ore_rift')
    if name == 'wild_jungle':
        feats[9].insert(feats[9].index('minecraft:trees_jungle'), 'ata:torchwood_trees')
        b['effects']['fog_color'] = 0xC9A07A
        b['effects']['sky_color'] = 0x8FA0FF
        b['effects']['foliage_color'] = 0x2E8B2E
    if name == 'great_desert':
        b['effects']['sky_color'] = 0xF2C27A
        b['effects']['fog_color'] = 0xE8C890
    if name == 'the_mire':
        b['effects']['fog_color'] = 0x5E6B4A
        b['effects']['sky_color'] = 0x7A8A6A
        b['effects']['water_color'] = 0x3B4A2A
        b['effects']['water_fog_color'] = 0x2A3320
        b['effects']['grass_color'] = 0x5A6A3A
        b['effects']['foliage_color'] = 0x4A5A2E
    b['features'] = feats
    w(f'{D}/worldgen/biome/{name}.json', b)
    lang[f'biome.ata.{name}'] = title

# Same terrain as Large Biomes (so the desert is huge), with sand and sandstone in the desert and mud in the mire.
ns = rd(f'{MC}/data/minecraft/worldgen/noise_settings/large_biomes.json')
floor = {'type': 'minecraft:stone_depth', 'offset': 0, 'surface_type': 'floor', 'add_surface_depth': False, 'secondary_depth_range': 0}
under = {'type': 'minecraft:stone_depth', 'offset': 0, 'surface_type': 'floor', 'add_surface_depth': True, 'secondary_depth_range': 6}
deep = {'type': 'minecraft:stone_depth', 'offset': 0, 'surface_type': 'floor', 'add_surface_depth': True, 'secondary_depth_range': 30}
block = lambda n: {'type': 'minecraft:block', 'result_state': {'Name': n}}
ours = {'type': 'minecraft:condition', 'if_true': {'type': 'minecraft:above_preliminary_surface'}, 'then_run': {'type': 'minecraft:sequence', 'sequence': [
    {'type': 'minecraft:condition', 'if_true': {'type': 'minecraft:biome', 'biome_is': ['ata:great_desert']}, 'then_run': {'type': 'minecraft:sequence', 'sequence': [
        {'type': 'minecraft:condition', 'if_true': under, 'then_run': block('minecraft:sand')},
        {'type': 'minecraft:condition', 'if_true': deep, 'then_run': block('minecraft:sandstone')}]}},
    {'type': 'minecraft:condition', 'if_true': {'type': 'minecraft:biome', 'biome_is': ['ata:the_mire']}, 'then_run': {'type': 'minecraft:sequence', 'sequence': [
        {'type': 'minecraft:condition', 'if_true': floor, 'then_run': {'type': 'minecraft:sequence', 'sequence': [
            {'type': 'minecraft:condition', 'if_true': {'type': 'minecraft:noise_threshold', 'noise': 'minecraft:surface_swamp', 'min_threshold': 0.0, 'max_threshold': 1.0e9}, 'then_run': block('minecraft:mud')},
            block('minecraft:grass_block')]}},
        {'type': 'minecraft:condition', 'if_true': under, 'then_run': block('minecraft:mud')}]}}]}}
ns['surface_rule']['sequence'].insert(1, ours)
w(f'{D}/worldgen/noise_settings/wilds.json', ns)

P = lambda t, h, c, e=(-1, 1), wd=(-1, 1): {'temperature': list(t), 'humidity': list(h), 'continentalness': list(c), 'erosion': list(e),
                                            'weirdness': list(wd), 'depth': 0, 'offset': 0}
w(f'{D}/dimension/wilds.json', {'type': 'minecraft:overworld', 'generator': {'type': 'minecraft:noise', 'settings': 'ata:wilds', 'biome_source': {
    'type': 'minecraft:multi_noise', 'biomes': [
        {'biome': 'ata:great_desert', 'parameters': P((0.05, 1), (-1, 1), (-0.11, 1))},
        {'biome': 'ata:wild_jungle', 'parameters': P((-1, 0.05), (-1, 0.3), (-0.11, 1))},
        {'biome': 'ata:the_mire', 'parameters': P((-1, 0.05), (0.3, 1), (-0.11, 1))},
        {'biome': 'ata:the_mire', 'parameters': P((-1, 1), (-1, 1), (-1.2, -0.11))}]}}})
lang['dimension.ata.wilds'] = 'The Wilds'

# ---------------------------------------------------------------- tags and lang
for (reg, tag), vals in tags.items():
    ns_, path = tag.split(':')
    p = f'{R}/data/{ns_}/tags/{reg}/{path}.json'
    old = rd(p)['values'] if os.path.exists(p) else []
    for v in vals:
        if v not in old:
            old.append(v)
    w(p, {'replace': False, 'values': old})

lp = f'{A}/lang/en_us.json'
L = rd(lp)
L.update(lang)
w(lp, L)
print('content done:', len(lang), 'lang entries')
