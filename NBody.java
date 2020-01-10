public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int numOfPlanet = in.readInt();
        double radius = in.readDouble();
        return radius;
    }


    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int numOfPlanet = in.readInt();
        double radius = in.readDouble();

        Body[] arr = new Body[numOfPlanet];
        int index = 0;

		while(index < numOfPlanet) {
            arr[index] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
            in.readDouble(), in.readString());
            
            index++;	
        }
        
        return arr;
    }

    public static void main(String[] args) {
        int time = 0;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Body[] bodies = NBody.readBodies(filename);
        double radius = NBody.readRadius(filename);

        String imageToDraw = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        // StdDraw.show();
        // for(int i = 0; i< bodies.length; i++) {
        //     bodies[i].draw();
        // }

        double[] xForces = new double[bodies.length];
        double[] yForces = new double[bodies.length]; 

        for(int i =time; i<= T; i+=dt) {
            for(int j = 0; j< bodies.length; j++) {
                xForces[j] = bodies[j].calcNetForceExertedByX(bodies);
                yForces[j] = bodies[j].calcNetForceExertedByY(bodies);
            }

            StdDraw.picture(0, 0, imageToDraw);
            for(int j = 0; j< bodies.length; j++) {
                bodies[j].update(dt, xForces[j], yForces[j]);
                bodies[j].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                        bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }
}