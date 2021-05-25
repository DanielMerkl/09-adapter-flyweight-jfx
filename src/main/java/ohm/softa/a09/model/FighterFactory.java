package ohm.softa.a09.model;

import javafx.scene.image.Image;
import ohm.softa.a09.model.empire.TieBomber;
import ohm.softa.a09.model.empire.TieFighter;
import ohm.softa.a09.model.empire.TieInterceptor;
import ohm.softa.a09.model.rebellion.AWing;
import ohm.softa.a09.model.rebellion.XWing;
import ohm.softa.a09.model.rebellion.YWing;
import ohm.softa.a09.resource.FxImageLoaderAdapter;
import ohm.softa.a09.util.NameGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Factory to create new fighters
 * Creates random fighters
 *
 * @author Peter Kurfer
 */
public final class FighterFactory {

	private static final int NumberOfKnownFighterTypes = 6;
	private final Random random;
	private final NameGenerator nameGenerator;
	private final FxImageLoaderAdapter fxImageLoaderAdapter = new FxImageLoaderAdapter();
	private final Map<String, Image> cache = new HashMap<>();

	public FighterFactory() {
		nameGenerator = new NameGenerator();
		random = new Random();
	}

	/**
	 * Create a random Fighter
	 *
	 * @return a new Fighter instance
	 * @implNote the method has an implementation flaw because it always loads the fighters image
	 */
	public Fighter createFighter() {
		String name = nameGenerator.generateName();

		switch (random.nextInt(NumberOfKnownFighterTypes)) {
			case 0:
				return new AWing(name, loadImage("fighter/awing.jpg"));
			case 1:
				return new XWing(name, loadImage("fighter/xwing.jpg"));
			case 2:
				return new YWing(name, loadImage("fighter/ywing.jpg"));
			case 3:
				return new TieBomber(name, loadImage("fighter/tiebomber.jpg"));
			case 4:
				return new TieFighter(name, loadImage("fighter/tiefighter.jpg"));
			default:
				return new TieInterceptor(name, loadImage("fighter/tieinterceptor.jpg"));
		}
	}

	private Image loadImage(String path) {
		if (!cache.containsKey(path)) {
			Image image = fxImageLoaderAdapter.loadImage(path);
			cache.put(path, image);

		}

		return cache.get(path);
	}
}
