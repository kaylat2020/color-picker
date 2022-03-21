package com.example.colorpicker;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

public class ScoreBlock implements Parcelable
{
    /**
     * This field is needed for Android to be able to
     * create new objects, individually or as arrays
     *
     * If you don’t do that, Android framework will raises an exception
     * Parcelable protocol requires a Parcelable.Creator object called CREATOR
     */
    public static final Parcelable.Creator<ScoreBlock> CREATOR
            = new Parcelable.Creator<ScoreBlock>()
    {
        public ScoreBlock createFromParcel(Parcel in)
        {
            return new ScoreBlock(in);
        }

        public ScoreBlock[] newArray(int size)
        {
            return new ScoreBlock[size];
        }
    };

    //[pts, color] is the order, always
    private String pts;
    private String color;
    private String[] block;
    private final DecimalFormat df = new DecimalFormat("#");

    //Constructor
    public ScoreBlock( String pts, String color )
    {
        block = new String[]{pts,color};
        this.pts = block[0];
        this.color = block[1];
    }

    //ALWAYS USE THESE
    public String[] getBlock() {
        return block;
    }
    public double getPoints()
    {
        //TODO this is causing problems on the current results screen
        return Double.valueOf(pts);
    }
    public int getColor()
    {
        return Integer.valueOf(color);
    }

    //PARCELABLE CODE (IGNORE 1/17/20)
    public ScoreBlock(Parcel in)
    {
        String[] data = new String[2];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.pts = data[0];
        this.color = data[1];
    }
    /**
     * Define the kind of object that you gonna parcel,
     * You can use hashCode() here
     */
    @Override
    public int describeContents()
    {
        return 0;
    }
    /**
     * Actual object serialization happens here, Write object content
     * to parcel, reading should be done according to this write order
     * @param dest - parcel
     * @param flags - Additional flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeStringArray( getBlock() );
    }
    @Override
    public String toString()
    {
        return "ScoreBlock{" +
                "pts= '" + this.pts+ '\'' +
                ", color= '" + this.color + '\'' +
                '}';
    }
}
