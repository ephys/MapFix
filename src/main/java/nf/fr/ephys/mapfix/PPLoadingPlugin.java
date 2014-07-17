package nf.fr.ephys.mapfix;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

public class PPLoadingPlugin implements IFMLLoadingPlugin {
	@Override
	public String[] getASMTransformerClass() {
		return new String[] { "nf.fr.ephys.playerproxies.common.core.ASMTransformer.ItemMapTransformer" };
	}

	@Override
	public String getModContainerClass() {
		return "nf.fr.ephys.mapfix.MapFix";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
