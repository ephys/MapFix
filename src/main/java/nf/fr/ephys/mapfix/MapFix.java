package nf.fr.ephys.mapfix;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.ModMetadata;

public class MapFix extends DummyModContainer {
	public MapFix() {
		super(new ModMetadata());

		ModMetadata meta = getMetadata();
		meta.modId = "mapfix";
		meta.name = "MapFix";
		meta.version = "1.0.0";
		meta.credits = "";
		meta.authorList.add("ephys");
		meta.description = "Removes the need to hold a map for it to draw";
		meta.url = "https://github.com/ephys/MapFix";
		meta.updateUrl = "";
		meta.screenshots = new String[0];
		meta.logoFile = "";
	}
}