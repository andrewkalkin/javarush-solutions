package com.javarush.task.task24.task2405;

/* 
Black box
*/
public class Solution implements Action {
    public static int countActionObjects;

    private int param;

    private Action solutionAction = new Action() {
        //!!!!! Changes can be here
        //!!!!! Изменения могут быть тут

        public void someAction() {
            //!!!!! All changes have to be here
            //!!!!! Все изменения должны быть только тут
            if (param > 0){
                while (param > 0) System.out.println(param--);
                FirstClass fClass = new FirstClass() {
                    @Override
                    public Action getDependantAction() {
                        return new Action()
                        {
                            @Override
                            public void someAction()
                            {
                            }
                        };
                    }

                    public void someAction() {
                        super.someAction();
                    }
                };

                fClass.someAction();
                SecondClass sClass = new SecondClass();
                sClass.someAction();
                System.out.println(sClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM.toString().substring(1) + param);
            }
            else
            {
                SecondClass sClass = new SecondClass();
                sClass.someAction();
                System.out.println(sClass.SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM.toString().substring(1) + param);
            }



        }
    };


    public Solution(int param) {
        this.param = param;
    }

    @Override
    public void someAction() {
        solutionAction.someAction();
    }

    /**
     * 5
     * 4
     * 3
     * 2
     * 1
     * class FirstClass, method someAction
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = 0
     * Count of created Action objects is 2
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = -1
     * Count of created Action objects is 3
     */
    public static void main(String[] args) {
        Solution solution = new Solution(5);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);

        solution = new Solution(-1);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);
    }
}
