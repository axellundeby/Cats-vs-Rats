package inf112.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Path {
    private Array<Vector2> points = new Array<>();
    private float totalLength;

    public Path() {
        this.totalLength = 0f;
    }

    public void addPoint(Vector2 point) {
        points.add(point);
        if (points.size > 1) {
            totalLength += point.dst(points.get(points.size - 2));
        }
    }

    public Array<Vector2> getPoints() {
        return points;
    }

    // Metode for å få posisjonen på stien basert på en gitt distanse
    public Vector2 getPosition(float distance) {
        if (points.size < 2) return null; // Trenger minst to punkter for å definere en sti

        float accumulatedLength = 0f;
        for (int i = 1; i < points.size; i++) {
            Vector2 start = points.get(i - 1);
            Vector2 end = points.get(i);
            float segmentLength = start.dst(end);

            if (accumulatedLength + segmentLength >= distance) {
                float remainingDistance = distance - accumulatedLength;
                float fraction = remainingDistance / segmentLength;
                return new Vector2(start.x + (end.x - start.x) * fraction, start.y + (end.y - start.y) * fraction);
            }

            accumulatedLength += segmentLength;
        }

        return points.peek(); // Returnerer siste punkt hvis distansen overstiger total lengde
    }

    public float getTotalLength() {
        return totalLength;
    }
}
