package tk.erdmko.arcanoid.objects;

import java.io.Serializable;

/**
 * Created by erdmko on 22.01.15.
 */
public class Vector2d implements Serializable {
    /** static temporary vector **/
    private final static Vector2d tmp = new Vector2d();

    /** the x-component of this vector **/
    public float x;
    /** the y-component of this vector **/
    public float y;

    /**
     * Constructs a new vector at (0,0)
     */
    public Vector2d()
    {

    }

    /**
     * Constructs a vector with the given components
     * @param x The x-component
     * @param y The y-component
     */
    public Vector2d(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a vector from the given vector
     * @param v The vector
     */
    public Vector2d( Vector2d v )
    {
        set( v );
    }

    /**
     * @return a copy of this vector
     */
    public Vector2d cpy( )
    {
        return new Vector2d( this );
    }

    /**
     * @return The euclidian length
     */
    public float len( )
    {
        return (float)Math.sqrt( x * x + y * y );
    }

    /**
     * @return The squared euclidian length
     */
    public float len2( )
    {
        return x * x + y * y;
    }

    /**
     * Sets this vector from the given vector
     * @param v The vector
     * @return This vector for chaining
     */
    public Vector2d set( Vector2d v )
    {
        x = v.x;
        y = v.y;
        return this;
    }

    /**
     * Sets the components of this vector
     * @param x The x-component
     * @param y The y-component
     * @return This vector for chaining
     */
    public Vector2d set( float x, float y )
    {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Substracts the given vector from this vector.
     * @param v The vector
     * @return This vector for chaining
     */
    public Vector2d sub( Vector2d v )
    {
        x -= v.x;
        y -= v.y;
        return this;
    }

    /**
     * Normalizes this vector
     * @return This vector for chaining
     */
    public Vector2d nor( )
    {
        float len = len( );
        if( len != 0 )
        {
            x /= len;
            y /= len;
        }
        return this;
    }

    /**
     * Adds the given vector to this vector
     * @param v The vector
     * @return This vector for chaining
     */
    public Vector2d add( Vector2d v )
    {
        x += v.x;
        y += v.y;
        return this;
    }

    /**
     * Adds the given components to this vector
     * @param x The x-component
     * @param y The y-component
     * @return This vector for chaining
     */
    public Vector2d add( float x, float y )
    {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * @param v The other vector
     * @return The dot product between this and the other vector
     */
    public float dot( Vector2d v )
    {
        return x * v.x + y * v.y;
    }

    /**
     * Multiplies this vector by a scalar
     * @param scalar The scalar
     * @return This vector for chaining
     */
    public Vector2d mul( float scalar )
    {
        x *= scalar;
        y *= scalar;
        return this;
    }
    public Vector2d mul( Vector2d scalar )
    {
        x *= scalar.x;
        y *= scalar.y;
        return this;
    }
    /**
     * @param v The other vector
     * @return the distance between this and the other vector
     */
    public float dst(Vector2d v)
    {
        float x_d = v.x - x;
        float y_d = v.y - y;
        return (float)Math.sqrt( x_d * x_d + y_d * y_d );
    }
    public Vector2d direction() {
        this.x /= Math.abs(this.x);
        this.y /= Math.abs(this.y);
        return this;
    }
    public Vector2d pow(float px) {
        this.x = (float) Math.pow(this.x, px);
        this.y = (float) Math.pow(this.y, px);
        return this;
    }
    public Vector2d pow(float px, float py) {
        this.x = (float) Math.pow(this.x, px);
        this.y = (float) Math.pow(this.y, py);
        return this;
    }
    /**
     * @param x The x-component of the other vector
     * @param y The y-component of the other vector
     * @return the distance between this and the other vector
     */
    public float dst( float x, float y )
    {
        float x_d = x - this.x;
        float y_d = y - this.y;
        return (float)Math.sqrt( x_d * x_d + y_d * y_d );
    }

    /**
     * @param v The other vector
     * @return the squared distance between this and the other vector
     */
    public float dst2(Vector2d v)
    {
        float x_d = v.x - x;
        float y_d = v.y - y;
        return x_d * x_d + y_d * y_d;
    }

    public String toString( )
    {
        return "[" + x + ":" + y + "]";
    }

    /**
     * Substracts the other vector from this vector.
     * @param x The x-component of the other vector
     * @param y The y-component of the other vector
     * @return This vector for chaining
     */
    public Vector2d sub(float x, float y)
    {
        this.x -= x;
        this.y -= y;
        return this;
    }

    /**
     * @return a temporary copy of this vector. Use with care as this is backed by a single static Vector2 instance. v1.tmp().add( v2.tmp() ) will not work!
     */
    public Vector2d tmp( )
    {
        return tmp.set(this);
    }
}

