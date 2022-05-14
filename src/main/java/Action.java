public class Action {
    enum CHOICE {
        EXIT, ADD, SHOW, EDIT, DELETE, NO_ACTION;

        public static CHOICE intValueOf(int input) {
            switch (input) {
                case 0 -> {
                    return EXIT;
                }
                case 1 -> {
                    return ADD;
                }
                case 2 -> {
                    return SHOW;
                }
                case 3 -> {
                    return EDIT;
                }
                case 4 -> {
                    return DELETE;
                }
                default -> {
                    return NO_ACTION;
                }
            }
        }
    }

    enum EDIT {
        EXIT, NAME, DAYS, PRIORITY, NO_ACTION;

        public static EDIT intValueOf(int input) {
            switch (input) {
                case 0 -> {
                    return EXIT;
                }
                case 1 -> {
                    return NAME;
                }
                case 2 -> {
                    return DAYS;
                }
                case 3 -> {
                    return PRIORITY;
                }
                default -> {
                    return NO_ACTION;
                }
            }
        }
    }
}