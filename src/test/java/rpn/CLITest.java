package rpn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static rpn.CLI.evaluate;

public class CLITest {

    @Test
    public void should_evaluate_single_digit_constant() {
        assertThat(evaluate("5")).isEqualTo(5);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        assertThat(evaluate("17")).isEqualTo(17);
    }

    @Test
    public void should_evaluate_negative_constant() {
        assertThat(evaluate("-5")).isEqualTo(-5);
    }

    @Test
    public void should_evaluate_simple_addition() {
        assertThat(evaluate("17 5 +")).isEqualTo(22);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        assertThat(evaluate("2 3 5 + +")).isEqualTo(10);
    }

    @Test
    public void should_evaluate_simple_subtraction() {
        assertThat(evaluate("17 5 -")).isEqualTo(12);
    }

    @Test
    public void should_evaluate_more_complex_subtraction() {
        assertThat(evaluate("2 5 3 - -")).isEqualTo(0);
    }

    @Test
    public void should_evaluate_more_complex_subtraction_with_negative_result() {
        assertThat(evaluate("2 2 5 - +")).isEqualTo(-1);
    }

    @Test
    public void should_evaluate_simple_multiplication() {
        assertThat(evaluate("3 6 *")).isEqualTo(18);
    }

    @Test
    public void should_evaluate_more_complex_multiplication() {
        assertThat(evaluate("6 3 5 + *")).isEqualTo(48);
    }

    @Test
    public void should_evaluate_simple_division() {
        assertThat(evaluate("20 5 /")).isEqualTo(4);
    }

    @Test
    public void should_evaluate_more_complex_division() {
        assertThat(evaluate("48 3 5 + /")).isEqualTo(6);
    }



    @Test(expected = ArithmeticException.class)
    public void should_not_evaluate_with_division_by_0() {
        evaluate("10 0 /");
    }

    @Test(expected = RuntimeException.class)
    public void should_not_evaluate_with_letters() {
        evaluate("2 3 P");
    }

    @Test(expected = RuntimeException.class)
    public void should_not_evaluate_with_operator_in_first() {
        evaluate("+ 2 3");
    }

    @Test(expected = RuntimeException.class)
    public void should_not_evaluate_with_classic_addition() {
        evaluate("2 + 3");
    }

    @Test(expected = RuntimeException.class)
    public void should_not_evaluate_with_too_much_operator_at_last() {
        evaluate("2 3 + +");
    }

    @Test(expected = RuntimeException.class)
    public void should_not_evaluate_with_too_much_number_at_last() {
        evaluate("2 3 + 4");
    }
}