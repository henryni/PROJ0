public class Body {
    static final double g = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;


    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public void draw() {
        String imageToDraw = "images/" + this.imgFileName;
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(xxPos, yyPos, imageToDraw);
        // StdDraw.show();
    }

    public double calcDistance(Body b){
        double dis = Math.sqrt((this.xxPos - b.xxPos) * (this.xxPos - b.xxPos) + (this.yyPos - b.yyPos) * (this.yyPos - b.yyPos));
        return dis;
    }

    public double calcForceExertedBy(Body b){
        double dis = this.calcDistance(b);
        double force = g * this.mass * b.mass / (dis * dis);
        return force;
    }

    public double calcForceExertedByX(Body b){
        double force = this.calcForceExertedBy(b);
        double forceX = force * (b.xxPos - this.xxPos) / this.calcDistance(b);
        return forceX;
    }

    public double calcForceExertedByY(Body b){
        double force = this.calcForceExertedBy(b);
        double forceY = force * (b.yyPos - this.yyPos) / this.calcDistance(b);
        return forceY;
    }

    public double calcNetForceExertedByX(Body[] b){
        double net = 0;
        for(Body bd : b){
            if(bd.equals(this)) continue;
            net = net + this.calcForceExertedByX(bd);
        }

        return net;
    }

    public double calcNetForceExertedByY(Body[] b){
        double net = 0;
        for(Body bd : b){
            if(bd.equals(this)) continue;
            net = net + this.calcForceExertedByY(bd);
        }

        return net;
    }

    public void update(double dt, double fX, double fY) {
        double AnetX = fX / this.mass;
        double AnetY = fY / this.mass;
        this.xxVel = this.xxVel + dt * AnetX;
        this.yyVel = this.yyVel + dt * AnetY;
        this.xxPos = xxPos + dt * this.xxVel;
        this.yyPos = yyPos + dt * this.yyVel;

    }

}