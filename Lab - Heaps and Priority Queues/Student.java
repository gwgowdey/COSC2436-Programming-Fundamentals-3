public class Student implements IPriority, IKey<Integer> {

    private static boolean keyIsId = true;          // Should the id be used as the key
    private static boolean usePriority = false;     // Should we use the priority for the key
    private int id;                                 // id of student
    private double gpa;                             // gpa of student
    private Classification classification;          // classification of student (Fresheman, Sophomore, etc)

    /**
     * The parameter constructor
     * @param id
     * @param gpa
     * @param classification
     */
    public Student(int id, double gpa, Classification classification) {
        this.id = id;
        this.gpa = gpa;
        this.classification = classification;
    }

    public static boolean isKeyIsId() {
        return keyIsId;
    }

    public static void setKeyIsId(boolean keyIsId) {
        Student.keyIsId = keyIsId;
    }
    
    public static boolean usePriority() {
        return usePriority;
    }
    
    public static void setUsePriority(boolean usePriority) {
        Student.usePriority = usePriority;
    }

    public int getId() {
        return id;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    /**
     * Overrides key method in IKey
     * uses id as key if keyIsId is true otherwise uses gpa as key
     * @return
     */
    @Override
    public Integer key() {
        if (usePriority) {
            return getPriority();
        }
        else if (keyIsId) {
            return id;
        }
        else {
            return Integer.valueOf((int)(gpa * 1000));
        }
    }

    /**
     * Overrides getPriority in IPriority.
     * @return The level values associated with the classification of the student. 0 is Doctorial candidate, 5 is Freshman
     */
    @Override
    public Integer getPriority() {
        return classification.getLevel();
    }

    /**
     * setPriority is not implemented as the priority is determined from the classification
     * @param priority
     */
    @Override
    public void setPriority(Integer priority) {
        throw new UnsupportedOperationException("Priority is the classification. Must be set when constructed");
    }

    @Override
    public String toString() {
        return "" + id + " " + gpa + " " + classification.toString();
    }

    /**
     * enumeration type for classification
     */
    public enum Classification {
        FRESHMAN("Freshman", 5),
        SOPHOMORE("Sophomore", 4),
        JUNIOR("Junior", 3),
        SENIOR("Senior", 2),
        GRAD("Graduate student", 1),
        POSTGRAD("Doctorial Candidate", 0);

        private String code;
        private int level;

        Classification(String code, int level) {
            this.code = code;
            this.level = level;
        }

        public int getLevel() {
            return level;
        }

        @Override
        public String toString() {
            return code;
        }
    }
}
