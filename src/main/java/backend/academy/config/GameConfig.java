package backend.academy.config;

import backend.academy.data.Word;
import backend.academy.data.enums.WordTheme;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MultipleStringLiterals")
public class GameConfig {
    public static final String HELP_COMMAND = "help";

    public static final String EXIT_COMMAND = "exit";

    public static final WordTheme[] THEMES = WordTheme.values();

    public static final Integer STAGES = 12;

    public static final Integer EASY_MODE_STEPS = 1;

    public static final Integer MEDIUM_MODE_STEPS = 2;

    public static final Integer HARD_MODE_STEPS = 3;

    public static final Word[] EASY_WORDS = {
        new Word("Sun", WordTheme.SCIENCE, "The star at the center of our solar system."),
        new Word("Star", WordTheme.SCIENCE, "A celestial body that emits light."),
        new Word("Atom", WordTheme.SCIENCE, "The smallest unit of matter."),
        new Word("Moon", WordTheme.SCIENCE, "Earth's only natural satellite."),
        new Word("Bread", WordTheme.FOOD, "A staple food made from flour and water."),
        new Word("Milk", WordTheme.FOOD, "A dairy product often added to coffee or cereal."),
        new Word("Apple", WordTheme.FOOD, "A fruit that keeps the doctor away."),
        new Word("Leaf", WordTheme.BIOLOGY, "Part of a plant that carries out photosynthesis."),
        new Word("Cat", WordTheme.ANIMALS, "A common pet that purrs."),
        new Word("Cell", WordTheme.BIOLOGY, "The basic structural unit of all living organisms."),
        new Word("Dog", WordTheme.ANIMALS, "A loyal pet, often called man's best friend."),
        new Word("Heart", WordTheme.BIOLOGY, "An organ that pumps blood throughout the body."),
        new Word("Cow", WordTheme.ANIMALS, "A farm animal that produces milk."),
        new Word("Rice", WordTheme.FOOD, "A staple grain, especially in Asia."),
        new Word("Bone", WordTheme.BIOLOGY, "The rigid organ that constitutes part of the skeleton."),
        new Word("Fish", WordTheme.ANIMALS, "An aquatic animal with gills."),
        new Word("Light", WordTheme.SCIENCE, "The natural agent that makes things visible."),
        new Word("Pizza", WordTheme.FOOD, "A popular Italian dish with cheese and toppings."),
        new Word("Skin", WordTheme.BIOLOGY, "The body's largest organ, covering the exterior."),
        new Word("Lion", WordTheme.ANIMALS, "The king of the jungle."),
        new Word("Sword", WordTheme.HISTORY, "A weapon used by medieval knights."),
        new Word("King", WordTheme.HISTORY, "Ancient leader of the country")

    };

    public static final Word[] MEDIUM_WORDS = {
        new Word("Gravity", WordTheme.SCIENCE, "The force that attracts objects towards Earth."),
        new Word("Neuron", WordTheme.BIOLOGY, "A nerve cell that transmits signals in the body."),
        new Word("Penguin", WordTheme.ANIMALS, "A flightless bird that lives in Antarctica."),
        new Word("Galaxy", WordTheme.SCIENCE, "A system of millions or billions of stars."),
        new Word("Curry", WordTheme.FOOD, "A dish popular in South Asian cuisine, known for its spices."),
        new Word("Sushi", WordTheme.FOOD, "A Japanese dish made with rice and seafood."),
        new Word("Chromosome", WordTheme.BIOLOGY, "A structure of nucleic acids carrying genetic information."),
        new Word("Quantum", WordTheme.SCIENCE, "A branch of physics dealing with atomic and subatomic particles."),
        new Word("Lasagna", WordTheme.FOOD, "An Italian dish with layers of pasta, cheese, and meat."),
        new Word("Giraffe", WordTheme.ANIMALS, "The tallest land animal with a long neck."),
        new Word("Baguette", WordTheme.FOOD, "A long, narrow loaf of French bread."),
        new Word("Enzyme", WordTheme.BIOLOGY, "A protein that accelerates chemical reactions in the body."),
        new Word("DNA", WordTheme.BIOLOGY, "The molecule that carries genetic instructions."),
        new Word("Dolphin", WordTheme.ANIMALS, "A highly intelligent marine mammal."),
        new Word("Orbit", WordTheme.SCIENCE, "The path of a celestial body around another."),
        new Word("Elephant", WordTheme.ANIMALS, "The largest land animal with a trunk."),
        new Word("Voltage", WordTheme.SCIENCE, "The electric potential difference between two points."),
        new Word("Taco", WordTheme.FOOD, "A Mexican dish with a folded or rolled tortilla."),
        new Word("Mitochondria", WordTheme.BIOLOGY, "The powerhouse of the cell."),
        new Word("Octopus", WordTheme.ANIMALS, "A marine animal with eight arms."),
        new Word("Crusade", WordTheme.HISTORY, "A religious war in medieval times."),
        new Word("Viking", WordTheme.HISTORY, "A Scandinavian explorer and raider."),
    };

    public static final Word[] HARD_WORDS = {
        new Word("Relativity", WordTheme.SCIENCE, "Einstein's theory that space and time are interwoven."),
        new Word("Apoptosis", WordTheme.BIOLOGY, "The process of programmed cell death."),
        new Word("Bouillabaisse", WordTheme.FOOD, "A traditional Provençal fish stew originating from France."),
        new Word("Orangutan", WordTheme.ANIMALS, "A great ape found in the rainforests of Borneo and Sumatra."),
        new Word("Photon", WordTheme.SCIENCE, "A quantum of light or other electromagnetic radiation."),
        new Word("Tiramisu", WordTheme.FOOD, "An Italian dessert made with coffee, cheese, and cocoa."),
        new Word("Hemoglobin", WordTheme.BIOLOGY, "The protein in red blood cells that carries oxygen."),
        new Word("Guillotine", WordTheme.HISTORY, "A device used for executions during the French Revolution."),
        new Word("Absolutism", WordTheme.HISTORY, "A political system where the monarch holds total power."),
        new Word("Entanglement", WordTheme.SCIENCE, "A quantum phenomenon where particles remain interconnected."),
        new Word("Paella", WordTheme.FOOD, "A Spanish rice dish originally from Valencia."),
        new Word("Axolotl", WordTheme.ANIMALS, "A type of salamander known for its regenerative abilities."),
        new Word("Thermodynamics", WordTheme.SCIENCE, "The study of heat and energy transfer."),
        new Word("Chameleon", WordTheme.ANIMALS, "A lizard known for changing its skin color."),
        new Word("Casumartzu", WordTheme.FOOD, "Traditional Sardinian sheep milk cheese ."),
        new Word("Platypus", WordTheme.ANIMALS, "An egg-laying mammal native to Australia."),
        new Word("Homeostasis", WordTheme.BIOLOGY, "The process of maintaining a stable internal environment."),
        new Word("Kimchi", WordTheme.FOOD, "A traditional Korean dish of fermented vegetables."),
        new Word("Synapse", WordTheme.BIOLOGY, "The junction between two neurons where signals are transmitted."),
        new Word("Fission", WordTheme.SCIENCE, "The splitting of an atomic nucleus, releasing energy."),
        new Word("Cytoplasm", WordTheme.BIOLOGY, "The material within a cell, excluding the nucleus."),
        new Word("Narwhal", WordTheme.ANIMALS,
            "A marine mammal known as the 'unicorn of the sea' due to its long tusk."),
    };
}
