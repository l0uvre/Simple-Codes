/**  LC 1344 -- Math. **/
public class AngleBetweenHandsClock {
    /** Hour hand : 
     * 1 hour = 360 / 12 = 30 degree;
     * 1 min  = 30 / 60 = 0.5 degree;
     * 
     * Min hand:
     * 
     * 1 min = 360 / 60 = 6 degree.
     * */
    public double angleClock(int hour, int minutes) {
        /** when hour is 12, we need to mod first. **/
        double degreeHour = (hour * (360 / 12)) % 360 + minutes *
            (30 / (double) 60);
        double degreeMin = minutes * (360 / 60);

        double diff = Math.abs(degreeMin - degreeHour);

        if (diff > 180) {
            return 360 - diff;
        } else {
            return diff;
        }
    }

    public static void main(String[] args) {
        AngleBetweenHandsClock sol = new AngleBetweenHandsClock();
        int hour = 12;
        int minutes = 30;
        System.out.println(sol.angleClock(hour, minutes));

        hour = 3;
        minutes = 30;
        System.out.println(sol.angleClock(hour, minutes));

        hour = 3;
        minutes = 15;
        System.out.println(sol.angleClock(hour, minutes));

        hour = 1;
        minutes = 57;
        System.out.println(sol.angleClock(hour, minutes));

    }
}
