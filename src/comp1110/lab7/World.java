package comp1110.lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a simple world in which the only organisms are
 * grass, rabbits, and foxes.
 * The world is divided into a Cartesian grid of sizeX * sizeY locations,
 * each of which contains an amount [0..5] of grass and may also contain an
 * animal, either a rabbit or a fox.
 * The locations in the world are laid out in a Cartesian grid with periodic
 * boundary conditions.
 */
public class World {
    static final int MAX_GRASS = 5;
    private static final double INITIAL_DENSITY_FOXES = 0.05;
    private static final double INITIAL_DENSITY_RABBITS = 0.4;
    private static final Random rand = new Random();

    /**
     * The size of this world in the x dimension
     * 世界的宽度
     */
    final int sizeX;
    /**
     * The size of this world in the y dimension
     * 世界的长度
     */
    final int sizeY;
    /**
     * The amount of grass at each location (x,y)
     * 在每个位置（x，y）处草的数量
     */
    private int[][] grass;
    /**
     * The animal at each location (x,y) (animals[x][y] == null if no animal at (x,y))
     * 每个位置处的动物数量
     */
    private Animal[][] animals;
    /**
     * An ordered list of animals from oldest to youngest.
     */
    private List<Animal> animalsByAge = new ArrayList<>();

    /**
     * An animal is an organism that can move around the world, consume other
     * organisms, and reproduce (creating a clone of itself).
     * Animals are born with a hunger level of MAX_HUNGER / 2.
     * 动物是可以在世界中移动，捕食其他生物，并且繁殖（创造一个自己的克隆体）。
     * 动物出身就带有 MAX_HUNGER / 2 的饥饿值。
     */
    abstract class Animal implements Cloneable {
        static final int MAX_HUNGER = 10;
        /**
         * This animal's x and y coordinates in the world
         * 动物在世界上的笛卡尔坐标位置
         */
        protected int x, y;
        /**
         * This animal's hunger level, [0..MAX_HUNGER]. A hunger level of
         * MAX_HUNGER will cause this animal to die of starvation.
         * 这是动物的饥饿值，从0到MAX_HUNGER， 如果饥饿值到达MAX_HUNGER， 动物就会因为饥饿而死亡。
         */
        protected int hunger;

        Animal(int x, int y) {
            this.x = x;
            this.y = y;
            this.hunger = MAX_HUNGER / 2;
            animals[x][y] = this;
        }

        /**
         * If there are any locations neighbouring the current location which do
         * not contain animals, randomly choose to move to one of the
         * empty locations.
         * If there are no neighbouring empty locations, then remain
         * in the current location.
         */
        protected void randomMove() {
            boolean[] empty = new boolean[9];
            boolean anyEmpty = false;
            int i = 0;
            for (int dX = -1; dX <= 1; dX++) {
                for (int dY = -1; dY <= 1; dY++) {
                    int neighbourX = (x + dX + sizeX) % sizeX;
                    int neighbourY = (y + dY + sizeY) % sizeY;
                    if (animals[neighbourX][neighbourY] == null) {
                        empty[i] = true;
                        anyEmpty = true;
                    }
                    i++;
                }
            }
            if (anyEmpty) {
                animals[x][y] = null;
                int move = rand.nextInt(9);
                while (!empty[move]) move = (move - 1 + 9) % 9;
                int xMove = move / 3 - 1;
                int yMove = move % 3 - 1;
                this.x = (x + xMove + sizeX) % sizeX;
                this.y = (y + yMove + sizeY) % sizeY;
                animals[x][y] = this;
            }
        }

        /**
         * Cause this animal to choose to move or stay in the current
         * location, and consume organisms in the target location.
         */
        abstract void moveAndEat();

        /**
         * If this animal's hunger level is zero, set this animal's hunger
         * level to MAX_HUNGER / 2 and then reproduce by creating a clone
         * of this animal and moving it to a random neighbouring empty location.
         * If there are no neighbouring empty locations, the child dies (i.e.
         * reproduction is unsuccessful).
         * 如果动物饥饿值为0， 将其饥饿值设置为最大值的一半然后繁殖出一个克隆体，
         * 并将克隆体随机移动到四周的空格。如果附近没有空格，后代死亡（也就是说繁殖不成功）
         *
         * @return the child that was reproduced, if any
         */
        Animal reproduce() {
            if (hunger == 0) {
                try {
                    hunger = MAX_HUNGER / 2;
                    Animal child = (Animal) this.clone();
                    child.randomMove();
                    if (animals[x][y] == null) {
                        animals[x][y] = this; // replace this animal in array
                        return child;
                    }
                    return null;
                } catch (CloneNotSupportedException e) {
                    System.err.println("this.clone() threw an exception!");
                }
            }
            return null;
        }
    }

    /**
     * A rabbit can move around the world, consume grass, and reproduce.
     */
    class Rabbit extends Animal {
        private Rabbit(int x, int y) {
            super(x, y);
        }

        /**
         * If the amount of grass in this rabbit's location is 1 or more,
         * reduce the amount of grass by one, and reduce this rabbit's hunger
         * level by 1 (to a minimum hunger level of 0).
         * 如果兔子所在位置草的数量大于等于1，草数量减一，兔子的饥饿值减一
         * Otherwise, move to a random neighbouring empty location (or stay
         * in the current location if there are no empty neighbouring
         * locations), and increase this rabbit's hunger level by 1 (to a
         * maximum hunger level of MAX_HUNGER).
         */
        void moveAndEat() {
            if (grass[x][y]>=1)
            {
                grass[x][y] = grass[x][y] - 1;
                hunger = hunger - 1;
            }
            else
            {
                randomMove();
                hunger = hunger + 1;
            }
            // FIXME complete this method
        }

        @Override
        public String toString() {
            return "Rabbit[" + x + "," + y + "]";
        }
    }

    /**
     * A fox can move around the world, consume rabbits, and reproduce.
     */
    class Fox extends Animal {
        private Fox(int x, int y) {
            super(x, y);
        }

        /**
         * Move to a neighbouring rabbit and consume it, or move at random.
         * If there are rabbits in any of the neighbouring locations, choose
         * a random neighbouring location containing a rabbit and move to it,
         * and reduce this fox's hunger by 1 (to a minimum hunger level of 0).
         * If there are no rabbits in any of the neighbouring locations, choose
         * to move to a random empty neighbouring location, or stay in the
         * current location, and increase this fox's hunger by 1 (to a maximum
         * hunger level of MAX_HUNGER).
         */
        void moveAndEat() {
            // FIXME complete this method
            boolean existRabbit = false;
            int rabbitX[] = new int[9];
            int rabbitY[] = new int[9];
            int rabbitNumber = 0;
            for (int dX = -1; dX <= 1; dX++) {
                for (int dY = -1; dY <= 1; dY++) {
                    int neighbourX = (x + dX + sizeX) % sizeX;
                    int neighbourY = (y + dY + sizeY) % sizeY;
                    if (animals[neighbourX][neighbourY] instanceof Rabbit) {
                        existRabbit = true;
                        rabbitX[rabbitNumber] = neighbourX;
                        rabbitY[rabbitNumber] = neighbourY;
                        rabbitNumber = rabbitNumber +1;
                    }
                }
            }
            if (existRabbit)
            {
                int random = 0 + (int)(Math.random() * (rabbitNumber-1));
                int randomX = rabbitX[random];
                int randomY = rabbitY[random];
                animals[randomX][randomY] = null;
                hunger = hunger -1;
                x = randomX;
                y = randomY;
            }
            else
            {
                randomMove();
                hunger = hunger + 1;
            }
        }

        @Override
        public String toString() {
            return "Fox[" + x + "," + y + "]";
        }
    }

    /**
     * Create a world of [sizeX*sizeY] locations, where each location contains
     * a random amount of grass [0..MAX_GRASS-1], and may also contain either
     * a rabbit or a fox.
     *
     * @param sizeX the size of the world in the x dimension
     * @param sizeY the size of the world in the y dimension
     */
    public World(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        grass = new int[sizeX][sizeY];
        animals = new Animal[sizeX][sizeY];
        // for each square:
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                // put a random amount of grass in this square
                grass[x][y] = rand.nextInt(MAX_GRASS);
                double d = rand.nextDouble();
                // with a probability of INITIAL_DENSITY_FOXES, put a fox in this square
                if (d < INITIAL_DENSITY_FOXES) {
                    createFox(x, y);
                    // with a probability of INITIAL_DENSITY_RABBITS, put a rabbit in this square
                } else if (d < (INITIAL_DENSITY_FOXES + INITIAL_DENSITY_RABBITS)) {
                    createRabbit(x, y);
                }
            }
        }
    }

    Rabbit createRabbit(int x, int y) {
        Rabbit rabbit = new Rabbit(x, y);
        animals[x][y] = rabbit;
        animalsByAge.add(animals[x][y]);
        return rabbit;
    }

    Fox createFox(int x, int y) {
        Fox fox = new Fox(x, y);
        animals[x][y] = fox;
        animalsByAge.add(animals[x][y]);
        return fox;
    }

    /**
     * Return a String representing the current state of this world.
     * The string is composed of (sizeX*sizeY) groups of three characters,
     * ordered first by column and then by row. For example, in a 3x3 world
     * the locations are ordered as follows:
     * 返回一个表示当前世界状态的字符串。
     * 字符串由三个字符组成(sizeX*sizeY)组，先按列排序，然后按行排序。
     * 例如，在3x3世界中，位置顺序如下:
     * <p>
     * 0 3 6
     * 1 4 7
     * 2 5 8
     * <p>
     * Each group of three characters is constructed as follows:
     * - the first character is a digit ['0'..'5'] representing the amount of grass
     * - the second character is one of [' ','r','f'] where:
     * * ' ' represents no animal
     * * 'r' represents a rabbit
     * * 'f' represents a fox
     * - the third character is either a space ' ' or a digit ['0'..'9'] which
     * represents the hunger level of the animal at this location. (If there is
     * no animal at this location, the third digit is a space.)
     * So for example, for a 3x3 world containing:
     * - a rabbit at location [0,2] with hunger level 5,
     * - a fox at location [2,1] with hunger level 8, and
     * - 4 grass at every location,
     * the String representation is: "4  4  4r54  4  4  4  4f84  "
     *
     * @return the string representation of the current state of the world
     */
    public String getCurrentState() {
        String state = new String("");
        for(int i = 0; i < sizeX; i++)
        {
            for(int k = 0; k < sizeY; k++)
            {
                String glass = String.valueOf(grass[i][k]);

                String animalType = new String();
                String animalHungry = new String();
                if(animals[i][k] == null)
                {
                    animalType = " ";
                    animalHungry = " ";
                }
                else if (animals[i][k] instanceof Rabbit ) {
                    animalType = "r";
                    animalHungry = String.valueOf(animals[i][k].hunger);
                }
                else if (animals[i][k] instanceof Fox ) {
                    animalType = "f";
                    animalHungry = String.valueOf(animals[i][k].hunger);
                }
                state = state + glass +animalType + animalHungry;
            }
        }
        return state;
        // FIXME 1: complete this method
    }

    /**
     * Update the current state of the world by making each animal move, eat,
     * and reproduce, and causing grass to grow.
     * First, each animal moves, eats, and reproduces (if possible) in order
     * from oldest to youngest.
     * Second, the amount of grass is increased by 1 in each square that does
     * not contain an animal.
     * Finally, any animal that has a hunger level of MAX_HUNGER is removed
     * from the world (as it has starved to death).
     */
    public void update() {
        // animals that existed at the start of the update and survived
        List<Animal> survivors = new ArrayList<>();
        // new animals created by reproduction this turn
        List<Animal> newGeneration = new ArrayList<>();
        for (Animal animal : animalsByAge) {
            animal.moveAndEat();
            Animal child = animal.reproduce();
            if (child != null) {
                newGeneration.add(child);
            }
        }
        int rabbits = 0;
        int foxes = 0;
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                Animal animal = animals[x][y];
                if (animal == null) {
                    // no animal in this location - the grass grows
                    grass[x][y] = Math.min(MAX_GRASS, grass[x][y] + 1);
                } else if (animal.hunger == Animal.MAX_HUNGER) {
                    // the animal in this location starved to death
                    animals[x][y] = null;
                } else {
                    // the animal in this location survived
                    survivors.add(animal);
                    if (animals[x][y] instanceof Rabbit) rabbits++;
                    else if (animals[x][y] instanceof Fox) foxes++;
                }
            }
        }
        // update the list of animals to include only survivors and new animals
        animalsByAge.retainAll(survivors);
        animalsByAge.addAll(newGeneration);

        System.out.println(rabbits + " rabbits " + foxes + " foxes");
    }

    /**
     * Package-protected constructor, used for testing only.
     * Create a new world with a constant amount of grass and no animals.
     */
    World(int sizeX, int sizeY, int grassAmount) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        grass = new int[sizeX][sizeY];
        animals = new Animal[sizeX][sizeY];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                grass[x][y] = grassAmount;
            }
        }
    }
}
