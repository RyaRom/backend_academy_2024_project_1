package backend.academy.utils;

import backend.academy.data.Word;
import java.util.Random;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameUtils {
    public static final Word[] EASY_WORDS = {
        new Word("Dog", "Animals", "Man's best friend."),
        new Word("Bird", "Animals", "It has feathers and can fly."),
        new Word("Lion", "Animals", "The king of the jungle."),
        new Word("Cat", "Animals", "A small domesticated carnivorous mammal."),
        new Word("Apple", "Food", "A common fruit that's often red or green."),
        new Word("Orange", "Food", "A citrus fruit that is also a color."),
        new Word("Milk", "Food", "A white liquid produced by mammals."),
        new Word("Bread", "Food", "A staple food made from flour and water."),
        new Word("House", "Household", "Where you live."),
        new Word("Book", "Household", "You read this."),
        new Word("Cup", "Household", "A small container used to drink from."),
        new Word("Pen", "Household", "You use this to write."),
        new Word("Bed", "Household", "You sleep on this."),
        new Word("Plate", "Household", "You eat food off of this."),
        new Word("Tree", "Nature", "It has leaves and provides shade."),
        new Word("Sun", "Nature", "The star at the center of our solar system."),
        new Word("River", "Nature", "A large stream of water flowing across the land."),
        new Word("Rain", "Nature", "Water falling from the sky in drops."),
        new Word("Cloud", "Nature", "A visible mass of condensed water vapor floating in the atmosphere."),
        new Word("Bike", "Vehicles", "A two-wheeled vehicle that you pedal."),
        new Word("Car", "Vehicles", "You drive this to go places."),
        new Word("Bus", "Vehicles", "A large vehicle that carries many passengers."),
        new Word("Train", "Vehicles", "A mode of transportation that runs on tracks.")
    };

    public static final Word[] MEDIUM_WORDS = {
        new Word("Elephant", "Animals", "The largest land animal with a trunk."),
        new Word("Falcon", "Animals", "A bird of prey known for its speed."),
        new Word("Dolphin", "Animals", "A highly intelligent marine mammal known for its playful nature."),
        new Word("Penguin", "Animals", "A flightless bird that lives in the Southern Hemisphere."),
        new Word("Mountain", "Geography", "A large natural elevation of the earth's surface."),
        new Word("Desert", "Geography", "A dry, sandy area with little rainfall."),
        new Word("Canyon", "Geography", "A deep gorge, typically with a river flowing through it."),
        new Word("Island", "Geography", "A piece of land surrounded by water."),
        new Word("Lantern", "Household", "A portable light source often used outdoors."),
        new Word("Scissors", "Household", "A tool used for cutting paper, cloth, or other materials."),
        new Word("Cushion", "Household", "A soft pillow or pad for sitting or leaning on."),
        new Word("Vase", "Household",
            "A decorative container, typically made of glass or ceramic, used to hold flowers."),
        new Word("Guitar", "Instruments", "A musical instrument with strings that you strum."),
        new Word("Piano", "Instruments", "A large keyboard musical instrument with black and white keys."),
        new Word("Violin", "Instruments", "A string instrument played with a bow."),
        new Word("Drum", "Instruments",
            "A percussion instrument typically made of a hollow cylinder with a membrane stretched over one or both ends."),
        new Word("Jungle", "Nature", "A dense, tropical forest."),
        new Word("Meadow", "Nature", "A field of grass and wildflowers."),
        new Word("Glacier", "Nature", "A slow-moving mass of ice formed by the accumulation and compaction of snow."),
        new Word("Waterfall", "Nature", "A stream of water falling from a height, often over a cliff."),
        new Word("Library", "Places", "A place where you can borrow books."),
        new Word("Museum", "Places",
            "A building where objects of historical, scientific, or artistic value are displayed."),
        new Word("Airport", "Places",
            "A complex of runways and buildings for the takeoff, landing, and maintenance of aircraft."),
        new Word("Harbor", "Places", "A sheltered place where ships may anchor."),
        new Word("Rainbow", "Weather", "A colorful arc seen after rain."),
        new Word("Tornado", "Weather",
            "A violently rotating column of air extending from a thunderstorm to the ground."),
        new Word("Hurricane", "Weather", "A type of storm known for its high winds and heavy rains."),
        new Word("Blizzard", "Weather", "A severe snowstorm with strong winds.")
    };

    public static final Word[] HARD_WORDS = {
        new Word("Photosynthesis", "Biology", "The process by which plants make food using sunlight."),
        new Word("Metamorphosis", "Biology", "A transformation, such as a caterpillar becoming a butterfly."),
        new Word("Chromosome", "Biology", "A structure within cells that contains genetic material."),
        new Word("Osmosis", "Biology", "The process by which molecules pass through a semipermeable membrane."),
        new Word("Labyrinth", "Mythology", "A complex maze in Greek mythology."),
        new Word("Phoenix", "Mythology", "A mythical bird that is reborn from its ashes."),
        new Word("Cerberus", "Mythology", "The three-headed dog that guards the entrance to the underworld."),
        new Word("Hydra", "Mythology", "A serpent-like creature with multiple heads that regrow when cut off."),
        new Word("Tsunami", "Natural Disasters", "A large sea wave caused by an underwater earthquake."),
        new Word("Earthquake", "Natural Disasters", "A sudden and violent shaking of the ground."),
        new Word("Volcano", "Natural Disasters", "A mountain that erupts with lava."),
        new Word("Avalanche", "Natural Disasters",
            "A mass of snow, ice, and rocks falling rapidly down a mountainside."),
        new Word("Allegiance", "Politics", "Loyalty or commitment to a superior or to a group or cause."),
        new Word("Parliament", "Politics", "The highest legislature in some countries."),
        new Word("Sovereignty", "Politics", "Supreme power or authority, especially of a state."),
        new Word("Diplomacy", "Politics", "The profession, activity, or skill of managing international relations."),
        new Word("Cryptography", "Science", "The art of writing or solving codes."),
        new Word("Hypothesis", "Science", "A proposed explanation for a phenomenon."),
        new Word("Quantum", "Science", "The smallest amount of a physical quantity that can exist independently."),
        new Word("Astronomy", "Science", "The study of celestial objects, space, and the universe as a whole.")
    };

    @SafeVarargs
    public static <T> T pickRandomObject(T... objects) {
        if (objects.length < 1) {
            throw new IllegalArgumentException("No random element in the empty array");
        }
        Random random = new Random();
        int index = random.nextInt(objects.length);
        return objects[index];
    }
}
