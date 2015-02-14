package tk.cvrunmin.lanfasy.client.renderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tk.cvrunmin.lanfasy.Lanfasy;
import tk.cvrunmin.lanfasy.client.model.ModelLFCreeper;
import tk.cvrunmin.lanfasy.client.renderer.layers.LayerLFCreeperCharge;
import tk.cvrunmin.lanfasy.entity.LFEntityCreeper;

@SideOnly(Side.CLIENT)
public class RenderLFCreeper extends RenderLiving
{
    private static final ResourceLocation creeperTextures = new ResourceLocation("lanfasy", "textures/entity/creeper/creeper.png");
    public RenderLFCreeper(RenderManager p_i46186_1_)
    {
        super(p_i46186_1_, new ModelLFCreeper(), 0.5F);
        this.addLayer(new LayerLFCreeperCharge(this));
    }
    protected void func_180570_a(LFEntityCreeper p_180570_1_, float p_180570_2_)
    {
        float f1 = p_180570_1_.getCreeperFlashIntensity(p_180570_2_);
        float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;
        f1 = MathHelper.clamp_float(f1, 0.0F, 1.0F);
        f1 *= f1;
        f1 *= f1;
        float f3 = (1.0F + f1 * 0.4F) * f2;
        float f4 = (1.0F + f1 * 0.1F) / f2;
        GlStateManager.scale(f3, f4, f3);
    }

    protected int func_180571_a(LFEntityCreeper p_180571_1_, float p_180571_2_, float p_180571_3_)
    {
        float f2 = p_180571_1_.getCreeperFlashIntensity(p_180571_3_);

        if ((int)(f2 * 10.0F) % 2 == 0)
        {
            return 0;
        }
        else
        {
            int i = (int)(f2 * 0.2F * 255.0F);
            i = MathHelper.clamp_int(i, 0, 255);
            return i << 24 | 16777215;
        }
    }
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(LFEntityCreeper par1EntityCreeper)
    {
        return creeperTextures;
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.func_180570_a((LFEntityCreeper)p_77041_1_, p_77041_2_);
    }

    protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_)
    {
        return this.func_180571_a((LFEntityCreeper)p_77030_1_, p_77030_2_, p_77030_3_);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((LFEntityCreeper)entity);
    }
}