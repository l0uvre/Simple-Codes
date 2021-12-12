/** LC 346 --- Queue, Design. **/
public class MovingAverage {
    Queue<Integer> q;
    int sum;
    int size;
    public MovingAverage(int size) {
        q = new LinkedList<>();
        this.size = size;
        sum = 0;

    }
    
    public double next(int val) {
        if (q.size() < size) {
            q.offer(val);
            sum += val;
        } else {
            int top = q.poll();
            q.offer(val);
            sum += (val - top);
        }
        return (double) sum / q.size();
    }
}
