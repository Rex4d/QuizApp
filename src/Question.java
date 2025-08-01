public class Question {
        String question;
        String[] options;
        int correctOption;

        public Question(String question, String[] options, int correctOption) {
            this.question = question;
            this.options = options;
            this.correctOption = correctOption;
        }

        public boolean isCorrect(int selectedOption) {
            return selectedOption == correctOption;
        }
    }

