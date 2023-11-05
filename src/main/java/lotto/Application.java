package lotto;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller();
        try {
            controller.lottoStart();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
