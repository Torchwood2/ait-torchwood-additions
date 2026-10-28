"""Placeholder textures for the Torchwood wood, Rift gear, guns and Wilds blocks. Original pixel art, made from
noise and hand-drawn masks, so an artist can replace any of them later with the same file names."""
import numpy as np, os, sys
from PIL import Image

R = sys.argv[1] if len(sys.argv) > 1 else 'src/main/resources'
TB = R + '/assets/ata/textures/block/'
TI = R + '/assets/ata/textures/item/'
os.makedirs(TB, exist_ok=True); os.makedirs(TI, exist_ok=True)
rng = np.random.default_rng(42)

def save(img, path):
    Image.fromarray(np.clip(img, 0, 255).astype(np.uint8), 'RGBA').save(path)

def pal(stops, t):
    t = np.clip(t, 0, 1); out = np.zeros(t.shape + (3,))
    for (a, ca), (b, cb) in zip(stops, stops[1:]):
        m = (t >= a) & (t <= b); f = ((t - a) / max(b - a, 1e-6))[m][:, None]
        out[m] = np.array(ca) * (1 - f) + np.array(cb) * f
    return out

def rgba(rgb, a=None):
    h, w = rgb.shape[:2]; o = np.zeros((h, w, 4)); o[..., :3] = rgb; o[..., 3] = 255 if a is None else a; return o

# ---- Torchwood (dark red-brown) ----
WOOD = [(0, (58, 22, 18)), (0.5, (110, 44, 32)), (1, (152, 72, 48))]
BARK = [(0, (30, 16, 14)), (0.6, (64, 34, 28)), (1, (92, 52, 40))]

def planks():
    t = np.zeros((16, 16))
    for b in range(4):
        y0 = b * 4; shade = rng.uniform(0.35, 0.65)
        for y in range(y0, y0 + 4):
            t[y] = shade + rng.normal(0, 0.06, 16) + (0.12 if y == y0 else 0) - (0.25 if y == y0 + 3 else 0)
        cut = rng.integers(3, 13); t[y0:y0 + 3, cut] -= 0.3
    return rgba(pal(WOOD, t))

def log_side(stops, stripes=True):
    t = np.zeros((16, 16))
    for x in range(16):
        base = rng.uniform(0.3, 0.7)
        t[:, x] = base + rng.normal(0, 0.08, 16)
        if stripes and rng.random() < 0.35: t[:, x] -= 0.3
    return rgba(pal(stops, t))

def log_top(inner, outer):
    y, x = np.mgrid[0:16, 0:16]; r = np.hypot(x - 7.5, y - 7.5)
    t = 0.45 + 0.2 * np.sin(r * 1.6) + rng.normal(0, 0.05, (16, 16))
    img = pal(inner, t)
    edge = (r > 6.6)
    img[edge] = pal(outer, 0.4 + rng.normal(0, 0.1, edge.sum()))
    return rgba(img)

def leaves():
    t = rng.random((16, 16))
    rgb = pal([(0, (90, 20, 12)), (0.5, (170, 54, 26)), (1, (232, 120, 48))], t)
    a = np.where(rng.random((16, 16)) < 0.18, 0, 255)
    return rgba(rgb, a)

def sapling():
    img = np.zeros((16, 16, 4))
    for y in range(8, 16): img[y, 7:9] = [70, 36, 28, 255]
    for (cx, cy, r) in [(8, 6, 4), (5, 8, 2.5), (11, 8, 2.5)]:
        yy, xx = np.mgrid[0:16, 0:16]; m = np.hypot(xx - cx, yy - cy) < r
        img[m] = np.concatenate([pal([(0, (130, 36, 20)), (1, (220, 100, 40))], rng.random(m.sum())), np.full((m.sum(), 1), 255)], 1)
    return img

def door(top):
    img = planks()
    img[:, :2, :3] *= 0.7; img[:, 14:, :3] *= 0.7
    if top:
        img[0:2, :, :3] *= 0.7
        for y0 in (3, 9):
            for x0 in (3, 9):
                img[y0:y0 + 4, x0:x0 + 4] = [0, 0, 0, 0]
    else:
        img[14:, :, :3] *= 0.7; img[7, 2:14, :3] *= 0.6
        img[8, 12] = [200, 170, 90, 255]
    return img

def trapdoor():
    img = planks(); img[:2, :, :3] *= 0.7; img[14:, :, :3] *= 0.7; img[:, :2, :3] *= 0.7; img[:, 14:, :3] *= 0.7
    for y0 in (4, 9):
        for x0 in (4, 9):
            img[y0:y0 + 3, x0:x0 + 3] = [0, 0, 0, 0]
    return img

def door_item():
    img = np.zeros((16, 16, 4)); p = planks()
    img[:, 4:12] = p[:, 4:12]
    for y in (2, 5): img[y:y + 2, 6:10] = [0, 0, 0, 0]
    img[9, 10] = [200, 170, 90, 255]
    return img

save(planks(), TB + 'torchwood_planks.png')
save(log_side(BARK), TB + 'torchwood_log.png')
save(log_top(WOOD, BARK), TB + 'torchwood_log_top.png')
save(log_side(WOOD, stripes=False), TB + 'stripped_torchwood_log.png')
save(log_top(WOOD, WOOD), TB + 'stripped_torchwood_log_top.png')
save(leaves(), TB + 'torchwood_leaves.png')
save(sapling(), TB + 'torchwood_sapling.png')
save(door(True), TB + 'torchwood_door_top.png')
save(door(False), TB + 'torchwood_door_bottom.png')
save(trapdoor(), TB + 'torchwood_trapdoor.png')
save(door_item(), TI + 'torchwood_door.png')

# ---- Rift (cyan crystal with violet depths) ----
RIFT = [(0, (40, 18, 80)), (0.45, (40, 110, 170)), (0.8, (80, 220, 230)), (1, (220, 255, 250))]

def stone(dark):
    base = [(0, (60, 60, 66)), (1, (130, 130, 136))] if not dark else [(0, (36, 36, 42)), (1, (80, 80, 88))]
    return pal(base, 0.5 + rng.normal(0, 0.15, (16, 16)))

def ore(dark):
    img = stone(dark)
    for _ in range(5):
        cx, cy = rng.integers(2, 14, 2)
        for dx, dy in [(0, 0), (1, 0), (0, 1), (-1, 0), (1, 1)]:
            if rng.random() < 0.8:
                x, y = np.clip(cx + dx, 0, 15), np.clip(cy + dy, 0, 15)
                img[y, x] = pal(RIFT, np.array([rng.uniform(0.5, 1.0)]))[0]
    return rgba(img)

def crystal_block():
    t = np.zeros((16, 16))
    for _ in range(9):
        cx, cy = rng.integers(0, 16, 2); yy, xx = np.mgrid[0:16, 0:16]
        t = np.maximum(t, 1 - (np.abs(xx - cx) + np.abs(yy - cy)) / rng.uniform(5, 9))
    t = 0.35 + 0.6 * t + rng.normal(0, 0.04, (16, 16))
    img = pal(RIFT, t); img[0, :] *= 0.6; img[-1, :] *= 0.6; img[:, 0] *= 0.6; img[:, -1] *= 0.6
    return rgba(img)

save(ore(False), TB + 'rift_ore.png')
save(ore(True), TB + 'deepslate_rift_ore.png')
save(crystal_block(), TB + 'rift_block.png')

def from_mask(rows, colors):
    img = np.zeros((16, 16, 4))
    for y, row in enumerate(rows):
        for x, ch in enumerate(row):
            if ch in colors: img[y, x] = list(colors[ch]) + [255]
    return img

C = {'#': (20, 40, 60), 'c': (80, 220, 230), 'C': (210, 255, 250), 'v': (60, 70, 170), 'h': (90, 56, 36), 'H': (60, 36, 24),
     'm': (60, 60, 70), 'M': (110, 110, 120), 'g': (170, 170, 180), 'r': (190, 60, 40), 'o': (230, 140, 50)}

SHARD = ["................", "........##......", ".......#Cc#.....", "......#Ccc#.....", ".....#Cccv#.....", "....#Cccv#......",
         "....#ccvv#......", "...#Ccvv#.......", "...#cvvv#.......", "..#ccvv#........", "..#cvv#.........", "..#vv#..........",
         "...##...........", "................", "................", "................"]
SWORD = ["..............##", ".............#C#", "............#Cc#", "...........#Cc#.", "..........#Cc#..", ".........#Cc#...",
         "........#Cc#....", ".......#Cc#.....", "..##..#Cc#......", "..#v##Cc#.......", "...#vvc#........", "....#hv#........",
         "...#h#vv#.......", "..#h#..##.......", ".#H#............", "..#............."]
PICK = ["....#####.......", "...#Cccccc#.....", "..#cc####cc#....", "..#v#..#H#cc#...", "...#..#H#.#c#...", ".....#H#...#v#..",
        "....#H#.....#...", "...#H#..........", "..#H#...........", ".#H#............", "#H#.............", ".#..............",
        "................", "................", "................", "................"]
AXE = ["......####......", ".....#Cccc#.....", "....#Ccc#c#.....", "....#ccv#H#.....", ".....#v#H#......", "......#H#.......",
       ".....#H#........", "....#H#.........", "...#H#..........", "..#H#...........", ".#H#............", "..#.............",
       "................", "................", "................", "................"]
SHOVEL = ["..........###...", ".........#Ccc#..", "........#Cccc#..", "........#ccvv#..", ".........#vv#...", "........#H##....",
          ".......#H#......", "......#H#.......", ".....#H#........", "....#H#.........", "...#H#..........", "..#H#...........",
          ".#H#............", "..#.............", "................", "................"]
HOE = ["......#####.....", ".....#Cccc#.....", "......####H#....", ".........#H#....", "........#H#.....", ".......#H#......",
       "......#H#.......", ".....#H#........", "....#H#.........", "...#H#..........", "..#H#...........", "..#.............",
       "................", "................", "................", "................"]
HELMET = ["................", "................", "....########....", "...#Ccccccvv#...", "..#Cccccccvvv#..", "..#ccvvvvvvvv#..",
          "..#cv######vv#..", "..#c#......#v#..", "..#v#......#v#..", "...#........#...", "................", "................",
          "................", "................", "................", "................"]
CHEST = ["................", "..###......###..", ".#Cc#......#vv#.", ".#ccc######cvv#.", ".#cccCccccccvv#.", "..##cccccccvv##.",
         "....#ccccccv#...", "....#Cccccvv#...", "....#ccccvvv#...", "....#cccvvvv#...", "....#ccvvvvv#...", "....########....",
         "................", "................", "................", "................"]
LEGS = ["................", "...##########...", "...#Ccccccvvv#..", "...#cccccccvv#..", "...#ccv##ccvv#..", "...#ccv##ccvv#..",
        "...#cv#..#cvv#..", "...#cv#..#cvv#..", "...#cv#..#cvv#..", "...#cv#..#cvv#..", "...#vv#..#vvv#..", "...####..#####..",
        "................", "................", "................", "................"]
BOOTS = ["................", "................", "................", "................", "................", "...####..####...",
         "...#Cv#..#cv#...", "...#cv#..#cv#...", "...#cv#..#cv#...", "..##cv#..#cv##..", ".#Ccvv#..#cvvc#.", ".#cvvv#..#vvvv#.",
         ".######..######.", "................", "................", "................"]
for name, rows in [('rift_shard', SHARD), ('rift_sword', SWORD), ('rift_pickaxe', PICK), ('rift_axe', AXE),
                   ('rift_shovel', SHOVEL), ('rift_hoe', HOE), ('rift_helmet', HELMET), ('rift_chestplate', CHEST),
                   ('rift_leggings', LEGS), ('rift_boots', BOOTS)]:
    save(from_mask(rows, C), TI + name + '.png')

# Guns: dark metal bodies with a glowing crystal accent.
PISTOL = ["................", "................", "................", "................", "..############..", "..#gMMMMMMMMMM#.",
          "..#MMMMMMcCmmm#.", "..##########mm#.", "..........#mHm#.", "..........#mHH#.", "...........#HH#.", "...........#HH#.",
          "...........####.", "................", "................", "................"]
CARBINE = ["................", "................", "................", "....######......", "#####MMMMMM#####", "#gMMMMMMMMMMMMM#",
           "#MMcCcMMMmmmmmm#", "####mm#####mm###", "...#mm#...#Hm#..", "...#mm#...#HH#..", "...####...#HH#..", "..........#HH#..",
           "..........####..", "................", "................", "................"]
LANCE = ["................", "................", "......####......", "......#vC#......", "################", "#gMMMMMMMMMMMMcC",
         "#MMMMMMMMMMMMMvc", "################", ".......#mm#.#H#.", ".......#mm#.#HH#", ".......####..#HH", ".............#HH",
         ".............###", "................", "................", "................"]
for name, rows in [('torchwood_service_pistol', PISTOL), ('rift_carbine', CARBINE), ('rift_lance', LANCE)]:
    save(from_mask(rows, C), TI + name + '.png')

# Armour layers (64x32): fill the standard biped armour UV boxes with crystal plating.
def plate(w, h):
    t = 0.45 + 0.35 * rng.random((h, w)); img = pal(RIFT, t)
    img[0, :] *= 0.55; img[-1, :] *= 0.55; img[:, 0] *= 0.55; img[:, -1] *= 0.55
    return img

def box_uv(img, u, v, sx, sy, sz):
    for (x, y, w, h) in [(u + sz, v, sx, sz), (u + sz + sx, v, sx, sz), (u, v + sz, sz, sy), (u + sz, v + sz, sx, sy),
                         (u + sz + sx, v + sz, sz, sy), (u + 2 * sz + sx, v + sz, sx, sy)]:
        if w > 0 and h > 0:
            img[y:y + h, x:x + w, :3] = plate(w, h); img[y:y + h, x:x + w, 3] = 255

l1 = np.zeros((32, 64, 4)); box_uv(l1, 0, 0, 8, 8, 8)       # helmet
box_uv(l1, 16, 16, 8, 12, 4)                                 # chest
box_uv(l1, 40, 16, 4, 12, 4)                                 # arms
box_uv(l1, 0, 16, 4, 12, 4)                                  # boots (legs box, lower part used)
l2 = np.zeros((32, 64, 4)); box_uv(l2, 16, 16, 8, 12, 4)     # leggings waist
box_uv(l2, 0, 16, 4, 12, 4)                                  # legs
os.makedirs(R + '/assets/minecraft/textures/models/armor', exist_ok=True)
save(l1, R + '/assets/minecraft/textures/models/armor/ata_rift_layer_1.png')
save(l2, R + '/assets/minecraft/textures/models/armor/ata_rift_layer_2.png')
print('textures done')
