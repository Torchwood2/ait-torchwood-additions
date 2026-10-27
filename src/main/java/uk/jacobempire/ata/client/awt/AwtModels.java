package uk.jacobempire.ata.client.awt;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;

/**
 * The AWT TARDIS models, converted from the Blockbench files (.bbmodel) that were in the repo, the same
 * way Blockbench's own "Java Entity" export does it. Generated: edit the .bbmodel and re-export rather
 * than editing these by hand.
 */
@Environment(EnvType.CLIENT)
public final class AwtModels {
    private AwtModels() {
    }

    /** From policebox.bbmodel. */
    public static TexturedModelData policeBox() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(-1.3F, 24.0F, 3.0F));
        ModelPartData sides = bone.addChild("sides", ModelPartBuilder.create()
        .uv(74, 135).cuboid(-10.0813F, -33.0F, 4.2813F, 2.0F, 31.0F, 1.0F, new Dilation(0.0F))
        .uv(48, 118).cuboid(-10.0813F, -34.0F, -10.7187F, 2.0F, 1.0F, 16.0F, new Dilation(0.0F))
        .uv(64, 49).cuboid(-9.5813F, -33.0F, -9.7187F, 1.0F, 31.0F, 14.0F, new Dilation(0.0F))
        .uv(0, 67).cuboid(10.4687F, -33.0F, -9.7187F, 0.0F, 31.0F, 14.0F, new Dilation(0.0F))
        .uv(94, 27).cuboid(-10.1313F, -33.0F, -9.7187F, 0.0F, 31.0F, 14.0F, new Dilation(0.0F))
        .uv(28, 67).cuboid(10.6687F, -33.0F, -9.7187F, 0.0F, 31.0F, 14.0F, new Dilation(0.0F))
        .uv(96, 138).cuboid(-10.0813F, -33.0F, -10.7187F, 2.0F, 31.0F, 1.0F, new Dilation(0.0F))
        .uv(102, 138).cuboid(8.9187F, -33.0F, -10.7187F, 2.0F, 31.0F, 1.0F, new Dilation(0.0F))
        .uv(122, 24).cuboid(8.9187F, -34.0F, -10.7187F, 2.0F, 1.0F, 16.0F, new Dilation(0.0F))
        .uv(108, 138).cuboid(8.9187F, -33.0F, 4.2813F, 2.0F, 31.0F, 1.0F, new Dilation(0.0F))
        .uv(80, 135).cuboid(10.6687F, -33.0F, -3.7187F, 0.0F, 31.0F, 2.0F, new Dilation(0.0F))
        .uv(114, 138).cuboid(-10.0813F, -33.0F, -3.7187F, 0.0F, 31.0F, 2.0F, new Dilation(0.0F))
        .uv(96, 135).cuboid(-7.5813F, -34.0F, 5.7813F, 16.0F, 1.0F, 2.0F, new Dilation(0.0F))
        .uv(50, 135).cuboid(7.4187F, -33.0F, 5.7813F, 1.0F, 31.0F, 2.0F, new Dilation(0.0F))
        .uv(104, 104).cuboid(-6.5813F, -33.0F, 7.7813F, 14.0F, 31.0F, 0.0F, new Dilation(0.0F))
        .uv(104, 72).cuboid(-6.5813F, -33.0F, 6.5313F, 14.0F, 31.0F, 1.0F, new Dilation(0.0F))
        .uv(126, 163).cuboid(0.45F, -15.0F, 7.0F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F))
        .uv(126, 158).cuboid(0.45F, -21.0F, 7.0F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F))
        .uv(126, 153).cuboid(0.45F, -27.0F, 7.0F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F))
        .uv(126, 148).cuboid(0.45F, -33.0F, 7.0F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F))
        .uv(56, 135).cuboid(-7.5813F, -33.0F, 5.7813F, 1.0F, 31.0F, 2.0F, new Dilation(0.0F))
        .uv(122, 41).cuboid(-0.5813F, -33.0F, 7.7813F, 2.0F, 31.0F, 0.0F, new Dilation(0.0F))
        .uv(126, 168).cuboid(0.45F, -9.0F, 7.0F, 0.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(1.3F, 0.0F, 0.0F));
        ModelPartData roof = bone.addChild("roof", ModelPartBuilder.create()
        .uv(0, 49).cuboid(-6.2813F, -4.5F, -10.8187F, 16.0F, 2.0F, 16.0F, new Dilation(0.0F))
        .uv(0, 27).cuboid(-7.2813F, -3.5F, -11.8187F, 18.0F, 4.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -37.0F, 0.1F));
        ModelPartData lantern = roof.addChild("lantern", ModelPartBuilder.create()
        .uv(72, 27).cuboid(-13.5813F, -42.5F, 23.2813F, 5.0F, 1.0F, 5.0F, new Dilation(0.0F))
        .uv(72, 43).cuboid(-12.5813F, -47.749F, 24.2813F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
        .uv(72, 33).cuboid(-13.0813F, -47.5F, 23.7813F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(12.8F, 37.0F, -28.6F));
        lantern.addChild("cube_r1", ModelPartBuilder.create().uv(72, 38).cuboid(0.0F, -5.0F, -3.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-13.2026F, -40.5F, 25.7958F, 0.0F, -0.7854F, 0.0F));
        lantern.addChild("cube_r2", ModelPartBuilder.create().uv(56, 75).cuboid(0.0F, -6.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-11.8026F, -40.5F, 25.0742F, 0.0F, -0.7854F, 0.0F));
        lantern.addChild("cube_r3", ModelPartBuilder.create().uv(56, 86).cuboid(-3.0F, -6.0F, -2.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)).uv(56, 67).cuboid(-1.0F, -6.0F, -4.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-11.7884F, -40.5F, 27.9026F, 0.0F, -0.7854F, 0.0F));
        ModelPartData PCB = roof.addChild("PCB", ModelPartBuilder.create()
        .uv(56, 94).cuboid(-9.7349F, -40.535F, -13.2651F, 3.0F, 3.0F, 21.0F, new Dilation(0.0F))
        .uv(96, 0).cuboid(10.2651F, -40.535F, -13.2651F, 3.0F, 3.0F, 21.0F, new Dilation(0.0F))
        .uv(0, 118).cuboid(-8.7349F, -40.535F, 5.7349F, 21.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 40.5464F, -0.1F));
        ModelPartData base = bone.addChild("base", ModelPartBuilder.create()
        .uv(0, 0).cuboid(-10.2813F, -3.0F, -14.7187F, 24.0F, 3.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData posts = bone.addChild("posts", ModelPartBuilder.create(), ModelTransform.pivot(-23.0F, 1.0F, -2.0F));
        posts.addChild("cube_r4", ModelPartBuilder.create().uv(24, 124).cuboid(-11.7651F, -38.6145F, -11.7651F, 3.0F, 36.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(4.9536F, -0.3855F, 19.0464F, 0.0F, -1.5708F, 0.0F));
        posts.addChild("cube_r5", ModelPartBuilder.create().uv(94, 72).cuboid(-10.7651F, -5.6145F, -10.7651F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(5.9536F, -35.3855F, 18.0464F, 0.0F, -1.5708F, 0.0F));
        posts.addChild("cube_r6", ModelPartBuilder.create().uv(56, 90).cuboid(-10.7651F, -5.6145F, -10.7651F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(23.9536F, -35.3855F, 18.0464F, 0.0F, -1.5708F, 0.0F));
        posts.addChild("cube_r7", ModelPartBuilder.create().uv(12, 124).cuboid(-11.7651F, -38.6145F, -11.7651F, 3.0F, 36.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(23.9536F, -0.3855F, 19.0464F, 0.0F, -1.5708F, 0.0F));
        posts.addChild("cube_r8", ModelPartBuilder.create().uv(84, 42).cuboid(-10.7651F, -5.6145F, -10.7651F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(5.9536F, -35.3855F, 0.0464F, 0.0F, -1.5708F, 0.0F));
        posts.addChild("cube_r9", ModelPartBuilder.create().uv(84, 38).cuboid(-10.7651F, -5.6145F, -10.7651F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(23.9536F, -35.3855F, 0.0464F, 0.0F, -1.5708F, 0.0F));
        ModelPartData front = bone.addChild("front", ModelPartBuilder.create()
        .uv(62, 135).cuboid(8.7187F, -33.0F, -13.2187F, 1.0F, 31.0F, 2.0F, new Dilation(0.0F))
        .uv(131, 136).cuboid(-7.0813F, -34.0F, -13.2187F, 17.0F, 1.0F, 2.0F, new Dilation(0.0F))
        .uv(68, 135).cuboid(-6.2813F, -33.0F, -13.2187F, 1.0F, 31.0F, 2.0F, new Dilation(0.0F))
        .uv(0, 112).cuboid(-8.7349F, -36.9886F, -14.2651F, 21.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        front.addChild("cube_r10", ModelPartBuilder.create().uv(0, 124).cuboid(-11.7651F, -38.6145F, -11.7651F, 3.0F, 36.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-18.0464F, 0.6145F, -1.9536F, 0.0F, -1.5708F, 0.0F));
        front.addChild("cube_r11", ModelPartBuilder.create().uv(84, 118).cuboid(-11.7651F, -38.6145F, -11.7651F, 3.0F, 36.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.9536F, 0.6145F, -1.9536F, 0.0F, -1.5708F, 0.0F));
        ModelPartData doors = front.addChild("doors", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData rightdoor = doors.addChild("rightdoor", ModelPartBuilder.create()
        .uv(126, 41).cuboid(5.9687F, -30.0F, -0.2687F, 2.0F, 30.0F, 0.0F, new Dilation(0.0F))
        .uv(134, 73).cuboid(-0.2813F, -30.0F, 0.0313F, 7.0F, 31.0F, 0.0F, new Dilation(0.0F))
        .uv(134, 41).cuboid(-0.2813F, -30.0F, 0.2813F, 7.0F, 31.0F, 1.0F, new Dilation(0.0F))
        .uv(80, 47).cuboid(5.6687F, -19.7071F, -0.6758F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
        .uv(80, 50).cuboid(1.6687F, -19.7071F, -0.6758F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -3.0F, -13.0F));
        rightdoor.addChild("cube_r12", ModelPartBuilder.create().uv(82, 50).cuboid(-1.7187F, 2.9719F, -12.043F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.3874F, -7.6642F, 4.2961F, -1.5708F, 0.0F, 0.0F));
        rightdoor.addChild("cube_r13", ModelPartBuilder.create().uv(84, 49).cuboid(-1.7187F, -12.3358F, -5.679F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.3874F, -23.3851F, 11.66F, 1.5708F, 0.0F, 0.0F));
        rightdoor.addChild("cube_r14", ModelPartBuilder.create().uv(84, 46).cuboid(-1.7187F, -12.3358F, -5.679F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.3874F, -23.3851F, 11.66F, 1.5708F, 0.0F, 0.0F));
        rightdoor.addChild("cube_r15", ModelPartBuilder.create().uv(82, 47).cuboid(-1.7187F, 2.9719F, -12.043F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.3874F, -7.6642F, 4.2961F, -1.5708F, 0.0F, 0.0F));
        ModelPartData phone = rightdoor.addChild("phone", ModelPartBuilder.create()
        .uv(88, 33).cuboid(-1.25F, -4.0F, -1.75F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
        .uv(94, 80).cuboid(-2.75F, -4.0F, -1.75F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
        .uv(56, 81).cuboid(-3.0F, -4.0F, -2.0F, 3.0F, 4.0F, 1.0F, new Dilation(0.0F))
        .uv(91, 98).cuboid(-3.5F, -4.75F, -1.75F, 4.0F, 5.0F, 2.0F, new Dilation(0.0F))
        .uv(1, 1).cuboid(-1.9F, -4.0F, -2.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
        .uv(1, 1).cuboid(-3.15F, -4.0F, -2.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
        .uv(2, 7).cuboid(-1.45F, -3.5F, -1.7F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
        .uv(2, 7).cuboid(-1.45F, -1.5F, -1.7F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
        .uv(2, 2).cuboid(-2.65F, -3.4F, -1.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
        .uv(2, 2).cuboid(-2.65F, -1.4F, -1.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(4.7187F, -16.25F, 3.0313F));
        phone.addChild("cube_r16", ModelPartBuilder.create().uv(95, 77).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.7929F, -0.7071F, -0.5F, 0.0F, 0.0F, -0.7854F));
        phone.addChild("cube_r17", ModelPartBuilder.create().uv(76, 47).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.5429F, -3.5F, -1.2071F, 0.0F, 0.7854F, 0.0F));
        phone.addChild("cube_r18", ModelPartBuilder.create().uv(72, 47).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.0429F, -3.5F, -1.2071F, 0.0F, 0.7854F, 0.0F));
        ModelPartData leftdoor = doors.addChild("leftdoor", ModelPartBuilder.create()
        .uv(132, 104).cuboid(-7.2813F, -30.0F, -0.7187F, 7.0F, 31.0F, 1.0F, new Dilation(0.0F))
        .uv(36, 135).cuboid(-7.2813F, -30.0F, -0.9687F, 7.0F, 31.0F, 0.0F, new Dilation(0.0F))
        .uv(86, 46).cuboid(-6.8313F, -19.5071F, -1.6758F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(9.0F, -3.0F, -12.0F));
        leftdoor.addChild("cube_r19", ModelPartBuilder.create().uv(86, 48).cuboid(-1.7187F, 3.9719F, -12.043F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.1126F, -7.2587F, 2.766F, -1.6144F, 0.0F, 0.0F));
        leftdoor.addChild("cube_r20", ModelPartBuilder.create().uv(84, 48).cuboid(-1.7187F, -11.3358F, -5.679F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.1126F, -23.1851F, 9.66F, 1.5708F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }

    /** From policebox_interior.bbmodel. */
    public static TexturedModelData policeBoxDoor() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(-1.3F, 26.0F, 6.0F));
        ModelPartData front = bone.addChild("front", ModelPartBuilder.create()
        .uv(62, 135).cuboid(8.7187F, -33.0F, -13.2187F, 1.0F, 31.0F, 2.0F, new Dilation(0.0F))
        .uv(131, 136).cuboid(-7.0813F, -34.0F, -13.2187F, 17.0F, 1.0F, 2.0F, new Dilation(0.0F))
        .uv(68, 135).cuboid(-6.2813F, -33.0F, -13.2187F, 1.0F, 31.0F, 2.0F, new Dilation(0.0F))
        .uv(0, 112).cuboid(-8.7349F, -36.9886F, -14.2651F, 21.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        front.addChild("cube_r1", ModelPartBuilder.create().uv(0, 124).cuboid(-11.7651F, -38.6145F, -11.7651F, 3.0F, 36.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-18.0464F, 0.6145F, -1.9536F, 0.0F, -1.5708F, 0.0F));
        front.addChild("cube_r2", ModelPartBuilder.create().uv(84, 118).cuboid(-11.7651F, -38.6145F, -11.7651F, 3.0F, 36.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.9536F, 0.6145F, -1.9536F, 0.0F, -1.5708F, 0.0F));
        ModelPartData doors = front.addChild("doors", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData rightdoor = doors.addChild("rightdoor", ModelPartBuilder.create()
        .uv(126, 41).cuboid(5.9687F, -30.0F, -0.2687F, 2.0F, 30.0F, 0.0F, new Dilation(0.0F))
        .uv(134, 73).cuboid(-0.2813F, -30.0F, 0.0313F, 7.0F, 31.0F, 0.0F, new Dilation(0.0F))
        .uv(134, 41).cuboid(-0.2813F, -30.0F, 0.2813F, 7.0F, 31.0F, 1.0F, new Dilation(0.0F))
        .uv(80, 47).cuboid(5.6687F, -19.7071F, -0.6758F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
        .uv(80, 50).cuboid(1.6687F, -19.7071F, -0.6758F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -3.0F, -13.0F));
        rightdoor.addChild("cube_r3", ModelPartBuilder.create().uv(82, 50).cuboid(-1.7187F, 2.9719F, -12.0429F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.3874F, -7.6642F, 4.2961F, -1.5708F, 0.0F, 0.0F));
        rightdoor.addChild("cube_r4", ModelPartBuilder.create().uv(84, 49).cuboid(-1.7187F, -12.3358F, -5.679F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.3874F, -23.3851F, 11.66F, 1.5708F, 0.0F, 0.0F));
        rightdoor.addChild("cube_r5", ModelPartBuilder.create().uv(84, 46).cuboid(-1.7187F, -12.3358F, -5.679F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.3874F, -23.3851F, 11.66F, 1.5708F, 0.0F, 0.0F));
        rightdoor.addChild("cube_r6", ModelPartBuilder.create().uv(82, 47).cuboid(-1.7187F, 2.9719F, -12.0429F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.3874F, -7.6642F, 4.2961F, -1.5708F, 0.0F, 0.0F));
        ModelPartData phone = rightdoor.addChild("phone", ModelPartBuilder.create()
        .uv(88, 33).cuboid(-1.25F, -4.0F, -1.75F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
        .uv(94, 80).cuboid(-2.75F, -4.0F, -1.75F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
        .uv(56, 81).cuboid(-3.0F, -4.0F, -2.0F, 3.0F, 4.0F, 1.0F, new Dilation(0.0F))
        .uv(91, 98).cuboid(-3.5F, -4.75F, -1.75F, 4.0F, 5.0F, 2.0F, new Dilation(0.0F))
        .uv(1, 1).cuboid(-1.9F, -4.0F, -2.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
        .uv(1, 1).cuboid(-3.15F, -4.0F, -2.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
        .uv(2, 7).cuboid(-1.45F, -3.5F, -1.7F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
        .uv(2, 7).cuboid(-1.45F, -1.5F, -1.7F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
        .uv(2, 2).cuboid(-2.65F, -3.4F, -1.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
        .uv(2, 2).cuboid(-2.65F, -1.4F, -1.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(4.7187F, -16.25F, 3.0313F));
        phone.addChild("cube_r7", ModelPartBuilder.create().uv(95, 77).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.7929F, -0.7071F, -0.5F, 0.0F, 0.0F, -0.7854F));
        phone.addChild("cube_r8", ModelPartBuilder.create().uv(76, 47).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.5429F, -3.5F, -1.2071F, 0.0F, 0.7854F, 0.0F));
        phone.addChild("cube_r9", ModelPartBuilder.create().uv(72, 47).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.0429F, -3.5F, -1.2071F, 0.0F, 0.7854F, 0.0F));
        ModelPartData leftdoor = doors.addChild("leftdoor", ModelPartBuilder.create()
        .uv(132, 104).cuboid(-7.2813F, -30.0F, -0.7187F, 7.0F, 31.0F, 1.0F, new Dilation(0.0F))
        .uv(36, 135).cuboid(-7.2813F, -30.0F, -0.9687F, 7.0F, 31.0F, 0.0F, new Dilation(0.0F))
        .uv(86, 46).cuboid(-6.8313F, -19.5071F, -1.6758F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(9.0F, -3.0F, -12.0F));
        leftdoor.addChild("cube_r10", ModelPartBuilder.create().uv(86, 48).cuboid(-1.7187F, 3.9719F, -12.0429F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.1126F, -7.2587F, 2.766F, -1.6144F, 0.0F, 0.0F));
        leftdoor.addChild("cube_r11", ModelPartBuilder.create().uv(84, 48).cuboid(-1.7187F, -11.3358F, -5.679F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.1126F, -23.1851F, 9.66F, 1.5708F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }

    /** From k6booth.bbmodel. */
    public static TexturedModelData phoneBooth() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        ModelPartData door = root.addChild("door", ModelPartBuilder.create()
        .uv(128, 143).cuboid(0.4467F, -39.0F, -0.7067F, 15.0F, 38.0F, 1.0F, new Dilation(0.0F))
        .uv(16, 17).cuboid(0.7667F, -39.0F, -0.6067F, 14.0F, 38.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-8.0F, 0.0F, -10.0F));
        ModelPartData frame = root.addChild("frame", ModelPartBuilder.create()
        .uv(106, 157).cuboid(7.5F, -39.0F, -10.5F, 3.0F, 38.0F, 3.0F, new Dilation(0.0F))
        .uv(160, 128).cuboid(-10.5F, -39.0F, -10.5F, 3.0F, 38.0F, 3.0F, new Dilation(0.0F))
        .uv(168, 39).cuboid(-10.5F, -39.0F, 7.5F, 3.0F, 38.0F, 3.0F, new Dilation(0.0F))
        .uv(160, 169).cuboid(7.5F, -39.0F, 7.5F, 3.0F, 38.0F, 3.0F, new Dilation(0.0F))
        .uv(0, 103).cuboid(-10.5F, -39.0F, -7.5F, 1.0F, 38.0F, 15.0F, new Dilation(0.0F))
        .uv(128, 104).cuboid(-7.5F, -39.0F, 9.5F, 15.0F, 38.0F, 1.0F, new Dilation(0.0F))
        .uv(32, 103).cuboid(9.5F, -39.0F, -7.5F, 1.0F, 38.0F, 15.0F, new Dilation(0.0F))
        .uv(82, 42).cuboid(-10.5F, -42.0F, -10.5F, 21.0F, 3.0F, 3.0F, new Dilation(0.0F))
        .uv(84, 79).cuboid(-10.0F, -47.0F, -10.0F, 1.0F, 5.0F, 20.0F, new Dilation(0.0001F))
        .uv(0, 79).cuboid(-10.5F, -50.0F, -10.5F, 21.0F, 3.0F, 21.0F, new Dilation(0.001F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        frame.addChild("cube_r1", ModelPartBuilder.create().uv(84, 79).cuboid(-10.0F, -47.0F, -10.0F, 1.0F, 5.0F, 20.0F, new Dilation(0.01F)).uv(82, 36).cuboid(-10.5F, -42.0F, -10.5F, 21.0F, 3.0F, 3.0F, new Dilation(0.01F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        frame.addChild("cube_r2", ModelPartBuilder.create().uv(84, 79).cuboid(-10.0F, -47.0F, -10.0F, 1.0F, 5.0F, 20.0F, new Dilation(0.001F)).uv(82, 36).cuboid(-10.5F, -42.0F, -10.5F, 21.0F, 3.0F, 3.0F, new Dilation(0.01F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
        frame.addChild("cube_r3", ModelPartBuilder.create().uv(84, 79).cuboid(-10.0F, -47.0F, -10.0F, 1.0F, 5.0F, 20.0F, new Dilation(0.0F)).uv(82, 36).cuboid(-10.5F, -42.0F, -10.5F, 21.0F, 3.0F, 3.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        ModelPartData bulb = frame.addChild("bulb", ModelPartBuilder.create()
        .uv(148, 24).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -45.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        ModelPartData phone2 = frame.addChild("phone2", ModelPartBuilder.create()
        .uv(64, 48).cuboid(10.0F, -17.0F, 14.0F, 5.0F, 1.0F, 6.0F, new Dilation(0.0F))
        .uv(172, 135).cuboid(14.0F, -23.0F, 14.0F, 1.0F, 6.0F, 6.0F, new Dilation(0.0F))
        .uv(134, 72).cuboid(10.0F, -24.0F, 14.0F, 5.0F, 1.0F, 6.0F, new Dilation(0.0F))
        .uv(164, 93).cuboid(0.0F, -24.0F, 14.0F, 5.0F, 1.0F, 6.0F, new Dilation(0.0F))
        .uv(172, 147).cuboid(0.0F, -23.0F, 14.0F, 1.0F, 6.0F, 6.0F, new Dilation(0.0F))
        .uv(172, 128).cuboid(0.0F, -17.0F, 14.0F, 5.0F, 1.0F, 6.0F, new Dilation(0.0F))
        .uv(164, 80).cuboid(5.0F, -24.0F, 16.0F, 5.0F, 9.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-7.5F, -1.0F, -11.5F));
        ModelPartData book = phone2.addChild("book", ModelPartBuilder.create()
        .uv(172, 159).cuboid(-2.0F, -5.0F, -2.5F, 2.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(12.0F, -17.0F, 17.5F, 0.0F, 0.0F, 0.4102F));
        ModelPartData phone = phone2.addChild("phone", ModelPartBuilder.create()
        .uv(148, 39).cuboid(-2.5F, -1.0F, 0.0F, 5.0F, 1.0F, 3.0F, new Dilation(0.0F))
        .uv(148, 30).cuboid(-1.5F, -3.0F, 1.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
        .uv(156, 72).cuboid(-5.5F, -2.0F, 1.5F, 4.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.5F, -24.0F, 15.5F, 0.0F, -0.6109F, 0.0F));
        ModelPartData recieverholder1 = phone.addChild("recieverholder1", ModelPartBuilder.create()
        .uv(148, 45).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, 1.0F, 0.7854F, 0.0F, 0.0F));
        ModelPartData recieverholder2 = phone.addChild("recieverholder2", ModelPartBuilder.create()
        .uv(156, 45).cuboid(-1.5F, -1.0F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, 2.0F, -0.7854F, 0.0F, 0.0F));
        ModelPartData dialangler = phone.addChild("dialangler", ModelPartBuilder.create(), ModelTransform.of(0.0F, -1.0F, 0.0F, -0.4363F, 0.0F, 0.0F));
        ModelPartData dial = dialangler.addChild("dial", ModelPartBuilder.create()
        .uv(86, 48).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));
        ModelPartData handset = phone.addChild("handset", ModelPartBuilder.create()
        .uv(148, 43).cuboid(-2.5F, -1.0F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
        .uv(148, 33).cuboid(-3.5F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
        .uv(148, 36).cuboid(1.5F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 1.5F));
        ModelPartData phone3 = frame.addChild("phone3", ModelPartBuilder.create()
        .uv(64, 0).cuboid(-11.0933F, 0.0F, -1.0F, 23.0F, 1.0F, 23.0F, new Dilation(0.0F))
        .uv(64, 104).cuboid(9.4067F, -38.0F, 3.0F, 1.0F, 38.0F, 15.0F, new Dilation(0.0F))
        .uv(96, 104).cuboid(-9.5933F, -38.0F, 3.0F, 1.0F, 38.0F, 15.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.4067F, -1.0F, -10.5F));
        return TexturedModelData.of(modelData, 256, 256);
    }

    /** From blueberrybox.bbmodel. */
    public static TexturedModelData blueberryBox() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData parent = modelPartData.addChild("parent", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        ModelPartData fade_value = parent.addChild("fade_value", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData right_door = parent.addChild("right_door", ModelPartBuilder.create()
        .uv(204, 208).cuboid(-12.0F, -52.0F, -0.5F, 12.0F, 52.0F, 0.0F, new Dilation(0.0F))
        .uv(152, 208).cuboid(-12.0F, -52.0F, -1.0F, 12.0F, 52.0F, 1.0F, new Dilation(0.0F))
        .uv(124, 64).cuboid(-11.5F, -30.0F, -3.0F, 0.0F, 5.0F, 2.0F, new Dilation(0.0F))
        .uv(132, 64).cuboid(-12.0F, -33.0F, -1.5F, 1.0F, 1.0F, 0.5F, new Dilation(0.0F)), ModelTransform.pivot(12.5F, -3.0F, -14.0F));
        ModelPartData left_door = parent.addChild("left_door", ModelPartBuilder.create()
        .uv(178, 208).cuboid(0.0F, -52.0F, -1.0F, 12.0F, 52.0F, 1.0F, new Dilation(0.0F))
        .uv(228, 129).cuboid(12.0F, -52.0F, -1.5F, 1.0F, 52.0F, 0.5F, new Dilation(0.0F))
        .uv(128, 64).cuboid(10.46F, -34.5F, -3.0F, 0.0F, 4.0F, 2.0F, new Dilation(0.0F))
        .uv(0, 210).cuboid(0.0F, -52.0F, -0.5F, 12.0F, 52.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-12.5F, -3.0F, -14.0F));
        ModelPartData frame = parent.addChild("frame", ModelPartBuilder.create()
        .uv(108, 104).cuboid(-15.0F, -55.0F, -12.5F, 1.0F, 52.0F, 25.0F, new Dilation(0.0F))
        .uv(0, 38).cuboid(-15.5F, -57.0F, -15.5F, 31.0F, 2.0F, 31.0F, new Dilation(0.0F))
        .uv(96, 210).cuboid(14.5F, -55.0F, -0.5F, 1.0F, 52.0F, 1.0F, new Dilation(0.0F))
        .uv(212, 155).cuboid(-15.5F, -55.0F, -0.5F, 1.0F, 52.0F, 1.0F, new Dilation(0.0F))
        .uv(228, 76).cuboid(-0.5F, -55.0F, 14.5F, 1.0F, 52.0F, 1.0F, new Dilation(0.0F))
        .uv(48, 218).cuboid(-15.5F, -55.0F, -15.5F, 3.0F, 52.0F, 3.0F, new Dilation(0.0F))
        .uv(60, 218).cuboid(-15.5F, -55.0F, 12.5F, 3.0F, 52.0F, 3.0F, new Dilation(0.0F))
        .uv(72, 218).cuboid(12.5F, -55.0F, 12.5F, 3.0F, 52.0F, 3.0F, new Dilation(0.0F))
        .uv(84, 218).cuboid(12.5F, -55.0F, -15.5F, 3.0F, 52.0F, 3.0F, new Dilation(0.0F))
        .uv(0, 0).cuboid(-17.5F, -3.0F, -17.5F, 35.0F, 3.0F, 35.0F, new Dilation(0.0F))
        .uv(0, 71).cuboid(-15.0F, -63.0F, -15.0F, 30.0F, 3.0F, 30.0F, new Dilation(0.0F))
        .uv(0, 104).cuboid(-13.5F, -64.5F, -13.5F, 27.0F, 2.0F, 27.0F, new Dilation(0.0F))
        .uv(120, 77).cuboid(-12.0F, -66.0F, -12.0F, 24.0F, 2.0F, 24.0F, new Dilation(0.0F))
        .uv(228, 182).cuboid(-4.0F, -67.0F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
        .uv(120, 71).cuboid(-2.0F, -69.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F))
        .uv(124, 44).cuboid(-1.5F, -72.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
        .uv(124, 38).cuboid(-2.0F, -74.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.0F))
        .uv(124, 60).cuboid(-1.5F, -75.0F, -1.5F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F))
        .uv(102, 181).cuboid(-12.5F, -55.0F, 14.5F, 25.0F, 52.0F, 0.0F, new Dilation(0.0F))
        .uv(216, 76).cuboid(13.5F, -62.0F, -16.5F, 3.0F, 59.0F, 3.0F, new Dilation(0.0F))
        .uv(190, 68).cuboid(-15.0F, -60.5F, -17.5F, 30.0F, 4.0F, 4.0F, new Dilation(0.0F))
        .uv(190, 0).cuboid(-17.5F, -60.5F, -15.0F, 4.0F, 4.0F, 30.0F, new Dilation(0.0F))
        .uv(24, 210).cuboid(-15.0F, -60.5F, 13.5F, 30.0F, 4.0F, 4.0F, new Dilation(0.0F))
        .uv(190, 34).cuboid(13.5F, -60.5F, -15.0F, 4.0F, 4.0F, 30.0F, new Dilation(0.0F))
        .uv(216, 138).cuboid(13.5F, -62.0F, 13.5F, 3.0F, 59.0F, 3.0F, new Dilation(0.0F))
        .uv(24, 218).cuboid(-16.5F, -62.0F, -16.5F, 3.0F, 59.0F, 3.0F, new Dilation(0.0F))
        .uv(36, 218).cuboid(-16.5F, -62.0F, 13.5F, 3.0F, 59.0F, 3.0F, new Dilation(0.0F))
        .uv(160, 155).cuboid(-12.5F, -55.0F, 14.0F, 25.0F, 52.0F, 1.0F, new Dilation(0.0F))
        .uv(0, 133).cuboid(14.0F, -55.0F, -12.5F, 1.0F, 52.0F, 25.0F, new Dilation(0.0F))
        .uv(52, 133).cuboid(14.5F, -55.0F, -12.5F, 0.0F, 52.0F, 25.0F, new Dilation(0.0F))
        .uv(140, 0).cuboid(-14.5F, -55.0F, -12.5F, 0.0F, 52.0F, 25.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData lampstruts_1 = frame.addChild("lampstruts_1", ModelPartBuilder.create()
        .uv(124, 50).cuboid(-3.0F, -4.0F, 0.0F, 6.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -69.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        ModelPartData lampstruts_2 = frame.addChild("lampstruts_2", ModelPartBuilder.create()
        .uv(124, 55).cuboid(-3.0F, -4.0F, 0.0F, 6.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -69.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        ModelPartData portal = modelPartData.addChild("portal", ModelPartBuilder.create()
        .uv(160, 103).cuboid(-14.0F, -55.0F, 0.0F, 28.0F, 52.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 512, 512);
    }

    /** From tardis.bbmodel. */
    public static TexturedModelData blocktis() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.0F, 0.0F));
        ModelPartData door = bone.addChild("door", ModelPartBuilder.create()
        .uv(64, 246).cuboid(0.0F, -8.0F, 0.0F, 16.0F, 32.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-8.0F, 0.0F, -24.0F));
        ModelPartData middle = bone.addChild("middle", ModelPartBuilder.create()
        .uv(100, 54).cuboid(8.0F, -8.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(100, 86).cuboid(8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(0, 102).cuboid(8.0F, -8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(64, 118).cuboid(-24.0F, -8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(128, 118).cuboid(-24.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(0, 134).cuboid(-24.0F, -8.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(64, 150).cuboid(-8.0F, -8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData toplayer = bone.addChild("toplayer", ModelPartBuilder.create()
        .uv(128, 150).cuboid(8.0F, -24.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(164, 54).cuboid(8.0F, -24.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(164, 86).cuboid(8.0F, -24.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(0, 166).cuboid(-24.0F, -24.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(64, 182).cuboid(-24.0F, -24.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(128, 182).cuboid(-24.0F, -24.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(192, 118).cuboid(-8.0F, -24.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(192, 150).cuboid(-8.0F, -24.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(192, 182).cuboid(-8.0F, -24.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData sign = toplayer.addChild("sign", ModelPartBuilder.create()
        .uv(0, 54).cuboid(-24.0F, -21.0F, -26.3F, 48.0F, 10.0F, 2.0F, new Dilation(0.0F))
        .uv(0, 66).cuboid(-24.0F, -21.0F, 24.8F, 48.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        sign.addChild("cube_r1", ModelPartBuilder.create().uv(0, 90).cuboid(-26.0F, 0.0F, -1.0F, 48.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-25.5F, -21.0F, -1.7F, 0.0F, 1.5708F, 0.0F));
        sign.addChild("cube_r2", ModelPartBuilder.create().uv(0, 78).cuboid(-26.0F, 0.0F, -1.0F, 48.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.5F, -21.0F, -1.7F, 0.0F, 1.5708F, 0.0F));
        ModelPartData bottomlayer = bone.addChild("bottomlayer", ModelPartBuilder.create()
        .uv(0, 198).cuboid(8.0F, 8.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(208, 0).cuboid(8.0F, 8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(64, 214).cuboid(8.0F, 8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(128, 214).cuboid(-24.0F, 8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(192, 214).cuboid(-24.0F, 8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(228, 32).cuboid(-24.0F, 8.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(228, 64).cuboid(-8.0F, 8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(0, 0).cuboid(-26.0F, 24.0F, -26.0F, 52.0F, 2.0F, 52.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData beacon = bone.addChild("beacon", ModelPartBuilder.create()
        .uv(0, 230).cuboid(-8.0F, -15.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(229, 96).cuboid(-6.0F, -1.0F, -6.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F))
        .uv(96, 246).cuboid(-5.0F, -11.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -25.0F, 0.0F));
        return TexturedModelData.of(modelData, 512, 512);
    }

    /** From tardisv2.bbmodel. */
    public static TexturedModelData blocktisV2() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.0F, 0.0F));
        ModelPartData door = bone.addChild("door", ModelPartBuilder.create()
        .uv(64, 246).cuboid(0.0F, -8.0F, 0.0F, 16.0F, 32.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-8.0F, 0.0F, -24.0F));
        ModelPartData middle = bone.addChild("middle", ModelPartBuilder.create()
        .uv(208, 0).cuboid(8.0F, -8.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(64, 214).cuboid(8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(128, 214).cuboid(8.0F, -8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(192, 214).cuboid(-24.0F, -8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(228, 32).cuboid(-24.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(228, 64).cuboid(-24.0F, -8.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(0, 230).cuboid(-8.0F, -8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData toplayer = bone.addChild("toplayer", ModelPartBuilder.create()
        .uv(336, 58).cuboid(8.0F, -32.0F, -24.0F, 16.0F, 8.0F, 16.0F, new Dilation(0.0F))
        .uv(336, 58).cuboid(-8.0F, -32.0F, -24.0F, 16.0F, 8.0F, 16.0F, new Dilation(0.0F))
        .uv(336, 58).cuboid(-24.0F, -32.0F, -24.0F, 16.0F, 8.0F, 16.0F, new Dilation(0.0F))
        .uv(336, 58).cuboid(-24.0F, -32.0F, 8.0F, 16.0F, 8.0F, 16.0F, new Dilation(0.0F))
        .uv(336, 58).cuboid(8.0F, -32.0F, 8.0F, 16.0F, 8.0F, 16.0F, new Dilation(0.0F))
        .uv(336, 58).cuboid(-8.0F, -32.0F, 8.0F, 16.0F, 8.0F, 16.0F, new Dilation(0.0F))
        .uv(336, 58).cuboid(8.0F, -32.0F, -8.0F, 16.0F, 8.0F, 16.0F, new Dilation(0.0F))
        .uv(336, 58).cuboid(-24.0F, -32.0F, -8.0F, 16.0F, 8.0F, 16.0F, new Dilation(0.0F))
        .uv(164, 54).cuboid(8.0F, -24.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(164, 86).cuboid(8.0F, -24.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(0, 166).cuboid(8.0F, -24.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(64, 182).cuboid(-24.0F, -24.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(128, 182).cuboid(-24.0F, -24.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(192, 118).cuboid(-24.0F, -24.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(192, 150).cuboid(-8.0F, -24.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(192, 182).cuboid(-8.0F, -24.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(0, 198).cuboid(-8.0F, -24.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData sign = toplayer.addChild("sign", ModelPartBuilder.create()
        .uv(0, 54).cuboid(-24.0F, -21.0F, -26.3F, 48.0F, 10.0F, 2.0F, new Dilation(0.0F))
        .uv(0, 66).cuboid(-24.0F, -21.0F, 24.8F, 48.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        sign.addChild("cube_r1", ModelPartBuilder.create().uv(0, 90).cuboid(-26.0F, 0.0F, -1.0F, 48.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-25.5F, -21.0F, -1.7F, 0.0F, 1.5708F, 0.0F));
        sign.addChild("cube_r2", ModelPartBuilder.create().uv(0, 78).cuboid(-26.0F, 0.0F, -1.0F, 48.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(25.5F, -21.0F, -1.7F, 0.0F, 1.5708F, 0.0F));
        ModelPartData bottomlayer = bone.addChild("bottomlayer", ModelPartBuilder.create()
        .uv(100, 86).cuboid(8.0F, 8.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(0, 102).cuboid(8.0F, 8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(64, 118).cuboid(8.0F, 8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(128, 118).cuboid(-24.0F, 8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(0, 134).cuboid(-24.0F, 8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(64, 150).cuboid(-24.0F, 8.0F, -24.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(128, 150).cuboid(-8.0F, 8.0F, 8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
        .uv(0, 0).cuboid(-26.0F, 24.0F, -26.0F, 52.0F, 2.0F, 52.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData lamp = bone.addChild("lamp", ModelPartBuilder.create()
        .uv(100, 54).cuboid(-8.0F, -15.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -25.0F, 0.0F));
        return TexturedModelData.of(modelData, 512, 512);
    }

    /** From type 70 mk2.bbmodel. */
    public static TexturedModelData type70() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create()
        .uv(0, 0).cuboid(-16.0F, -36.0F, -8.0F, 32.0F, 4.0F, 24.0F, new Dilation(0.0F))
        .uv(64, 52).cuboid(8.0F, -32.0F, -8.0F, 8.0F, 32.0F, 24.0F, new Dilation(0.0F))
        .uv(0, 28).cuboid(-16.0F, 0.1F, -8.0F, 32.0F, 0.0F, 24.0F, new Dilation(0.0F))
        .uv(0, 52).cuboid(-16.0F, -32.0F, -8.0F, 8.0F, 32.0F, 24.0F, new Dilation(0.0F))
        .uv(0, 108).cuboid(-8.0F, -32.0F, 0.0F, 16.0F, 32.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        ModelPartData door = bone.addChild("door", ModelPartBuilder.create()
        .uv(112, 0).cuboid(-15.0F, -0.01F, 83.0F, 16.0F, 0.0F, 15.0F, new Dilation(0.0F))
        .uv(64, 108).cuboid(-15.0F, -32.01F, 82.0F, 16.0F, 1.0F, 16.0F, new Dilation(0.0F))
        .uv(112, 15).cuboid(-15.0F, -31.01F, 82.0F, 16.0F, 31.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(7.0F, 0.0F, -90.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }

    /** From type70 doors.bbmodel. */
    public static TexturedModelData type70Door() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create()
        .uv(89, 152).cuboid(-16.0F, -36.0F, 0.0F, 32.0F, 4.0F, 1.0F, new Dilation(0.0F))
        .uv(119, 157).cuboid(7.0F, -32.0F, 0.0F, 9.0F, 32.0F, 1.0F, new Dilation(0.0F))
        .uv(89, 190).cuboid(-16.0F, -32.0F, 0.0F, 9.0F, 32.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, -8.0F));
        ModelPartData door = bone.addChild("door", ModelPartBuilder.create()
        .uv(89, 157).cuboid(-14.0F, -32.01F, 82.0F, 14.0F, 32.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(7.0F, 0.0F, -83.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }

    /** From Onion Model Interior.bbmodel. */
    public static TexturedModelData onion() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Root = modelPartData.addChild("Root", ModelPartBuilder.create()
        .uv(88, 44).cuboid(-5.0F, 0.0F, -5.0F, 10.0F, 35.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, 0.0F));
        Root.addChild("cube_r1", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, -7.0F, -7.0F, 1.0F, 14.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 37.0F, 0.0F, 1.5708F, 0.0F, 1.5708F));
        ModelPartData GetInLoserWereGoingShopping = Root.addChild("GetInLoserWereGoingShopping", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, 0.0F));
        GetInLoserWereGoingShopping.addChild("cube_r2", ModelPartBuilder.create().uv(0, -2).cuboid(0.0F, -4.0F, -3.5F, 0.0F, 8.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, 19.0F, 5.25F, 0.0F, -1.5708F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

}
