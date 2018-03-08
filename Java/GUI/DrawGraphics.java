package assignment6;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawGraphics {
  //Bouncer movingSprite;
  ArrayList<Mover> sprites = new ArrayList<>();

  /** Initializes this class for drawing. */
  public DrawGraphics() {
    Rectangle box = new Rectangle(15, 20, Color.RED);
    Oval circle = new Oval(25, 25, Color.BLUE);
    sprites.add(new Bouncer(100, 170, box));
    sprites.add(new Bouncer(120, 150, circle));
    sprites.get(0).setMovementVector(3, 1);
    sprites.get(1).setMovementVector(2, 2);
    sprites.add(new StraightMover(50, 120, box));
    sprites.add(new StraightMover(280, 250, circle));
    sprites.get(2).setMovementVector(1, 1);
    sprites.get(3).setMovementVector(-1, -1);
  }

  /** Draw the contents of the window on surface. */
  public void draw(Graphics surface) {
    for (Mover sprite : sprites) {
      sprite.draw(surface);
    }
  }
}