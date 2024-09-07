package backend.academy.config;

import backend.academy.data.Word;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MultipleStringLiterals")
public class GameConfig {
    public static final String[] THEMES = {"Science", "Food", "Biology", "Animals"};

    public static final Integer STAGES = 12;

    public static final Integer EASY_MODE_STEPS = 1;

    public static final Integer MEDIUM_MODE_STEPS = 2;

    public static final Integer HARD_MODE_STEPS = 3;

    public static final Word[] EASY_WORDS = {
        new Word("Sun", "Science", "The star at the center of our solar system."),
        new Word("Star", "Science", "A celestial body that emits light."),
        new Word("Atom", "Science", "The smallest unit of matter."),
        new Word("Moon", "Science", "Earth's only natural satellite."),
        new Word("Bread", "Food", "A staple food made from flour and water."),
        new Word("Milk", "Food", "A dairy product often added to coffee or cereal."),
        new Word("Apple", "Food", "A fruit that keeps the doctor away."),
        new Word("Leaf", "Biology", "Part of a plant that carries out photosynthesis."),
        new Word("Cat", "Animals", "A common pet that purrs."),
        new Word("Cell", "Biology", "The basic structural unit of all living organisms."),
        new Word("Dog", "Animals", "A loyal pet, often called man's best friend."),
        new Word("Heart", "Biology", "An organ that pumps blood throughout the body."),
        new Word("Cow", "Animals", "A farm animal that produces milk."),
        new Word("Rice", "Food", "A staple grain, especially in Asia."),
        new Word("Bone", "Biology", "The rigid organ that constitutes part of the skeleton."),
        new Word("Fish", "Animals", "An aquatic animal with gills."),
        new Word("Light", "Science", "The natural agent that makes things visible."),
        new Word("Pizza", "Food", "A popular Italian dish with cheese and toppings."),
        new Word("Skin", "Biology", "The body's largest organ, covering the exterior."),
        new Word("Lion", "Animals", "The king of the jungle."),
    };

    public static final Word[] MEDIUM_WORDS = {
        new Word("Gravity", "Science", "The force that attracts objects towards Earth."),
        new Word("Neuron", "Biology", "A nerve cell that transmits signals in the body."),
        new Word("Penguin", "Animals", "A flightless bird that lives in Antarctica."),
        new Word("Galaxy", "Science", "A system of millions or billions of stars."),
        new Word("Curry", "Food", "A dish popular in South Asian cuisine, known for its spices."),
        new Word("Sushi", "Food", "A Japanese dish made with rice and seafood."),
        new Word("Chromosome", "Biology", "A structure of nucleic acids carrying genetic information."),
        new Word("Quantum", "Science", "A branch of physics dealing with atomic and subatomic particles."),
        new Word("Lasagna", "Food", "An Italian dish with layers of pasta, cheese, and meat."),
        new Word("Giraffe", "Animals", "The tallest land animal with a long neck."),
        new Word("Baguette", "Food", "A long, narrow loaf of French bread."),
        new Word("Enzyme", "Biology", "A protein that accelerates chemical reactions in the body."),
        new Word("DNA", "Biology", "The molecule that carries genetic instructions."),
        new Word("Dolphin", "Animals", "A highly intelligent marine mammal."),
        new Word("Orbit", "Science", "The path of a celestial body around another."),
        new Word("Elephant", "Animals", "The largest land animal with a trunk."),
        new Word("Voltage", "Science", "The electric potential difference between two points."),
        new Word("Taco", "Food", "A Mexican dish with a folded or rolled tortilla."),
        new Word("Mitochondria", "Biology", "The powerhouse of the cell."),
        new Word("Octopus", "Animals", "A marine animal with eight arms."),
    };

    public static final Word[] HARD_WORDS = {
        new Word("Relativity", "Science", "Einstein's theory that space and time are interwoven."),
        new Word("Apoptosis", "Biology", "The process of programmed cell death."),
        new Word("Bouillabaisse", "Food", "A traditional Provençal fish stew originating from France."),
        new Word("Orangutan", "Animals", "A great ape found in the rainforests of Borneo and Sumatra."),
        new Word("Photon", "Science", "A quantum of light or other electromagnetic radiation."),
        new Word("Tiramisu", "Food", "An Italian dessert made with coffee, cheese, and cocoa."),
        new Word("Hemoglobin", "Biology", "The protein in red blood cells that carries oxygen."),
        new Word("Guillotine", "History", "A device used for executions during the French Revolution."),
        new Word("Entanglement", "Science", "A quantum phenomenon where particles remain interconnected."),
        new Word("Paella", "Food", "A Spanish rice dish originally from Valencia."),
        new Word("Axolotl", "Animals", "A type of salamander known for its regenerative abilities."),
        new Word("Thermodynamics", "Science", "The study of heat and energy transfer."),
        new Word("Chameleon", "Animals", "A lizard known for changing its skin color."),
        new Word("Casumartzu", "Food", "Traditional Sardinian sheep milk cheese ."),
        new Word("Platypus", "Animals", "An egg-laying mammal native to Australia."),
        new Word("Homeostasis", "Biology", "The process of maintaining a stable internal environment."),
        new Word("Kimchi", "Food", "A traditional Korean dish of fermented vegetables."),
        new Word("Synapse", "Biology", "The junction between two neurons where signals are transmitted."),
        new Word("Fission", "Science", "The splitting of an atomic nucleus, releasing energy."),
        new Word("Cytoplasm", "Biology", "The material within a cell, excluding the nucleus."),
        new Word("Narwhal", "Animals", "A marine mammal known as the 'unicorn of the sea' due to its long tusk."),
    };
}
