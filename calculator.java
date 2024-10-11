document.addEventListener('DOMContentLoaded', function () {
    const screen = document.querySelector('.calculator-screen');
    let memory = 0;
    let currentValue = '';
    let operator = null;

    const updateScreen = (value) => {
        screen.value = value;
    };

    const calculate = (a, b, operator) => {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            case 'x²':
                return Math.pow(a, 2);
            case '√':
                return Math.sqrt(a);
            default:
                return b;
        }
    };

    const handleOperator = (value) => {
        if (value === 'M+') {
            memory += parseFloat(currentValue);
            updateScreen(currentValue);
        } else if (value === 'M-') {
            memory -= parseFloat(currentValue);
            updateScreen(currentValue);
        } else if (value === 'RM') {
            currentValue = memory.toString();
            updateScreen(currentValue);
        } else if (value === '+/-') {
            currentValue = (parseFloat(currentValue) * -1).toString();
            updateScreen(currentValue);
        } else {
            if (operator !== null) {
                currentValue = calculate(parseFloat(memory), parseFloat(currentValue), operator).toString();
            }
            operator = value;
            memory = parseFloat(currentValue);
            currentValue = '';
        }
    };

    const handleEqual = () => {
        if (operator !== null) {
            currentValue = calculate(parseFloat(memory), parseFloat(currentValue), operator).toString();
            operator = null;
            memory = 0;
        }
        updateScreen(currentValue);
    };

    document.querySelectorAll('button').forEach(button => {
        button.addEventListener('click', (e) => {
            const value = e.target.value;

            if (e.target.classList.contains('operator')) {
                if (value === '=') {
                    handleEqual();
                } else {
                    handleOperator(value);
                }
            } else if (e.target.classList.contains('clear')) {
                currentValue = '';
                operator = null;
                memory = 0;
                updateScreen(currentValue);
            } else {
                currentValue += value;
                updateScreen(currentValue);
            }
        });
    });
});
