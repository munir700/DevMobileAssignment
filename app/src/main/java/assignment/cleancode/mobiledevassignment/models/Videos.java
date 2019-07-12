package assignment.cleancode.mobiledevassignment.models;


import com.google.gson.annotations.SerializedName;

public class Videos
{
    private String site;

    private String size;

    @SerializedName("iso_3166_1")
    private String iso31661;

    private String name;

    private String id;

    private String type;

    @SerializedName("iso_639_1")
    private String iso6391;

    private String key;

    public String getSite ()
    {
        return site;
    }

    public void setSite (String site)
    {
        this.site = site;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

    public String getIso31661()
    {
        return iso31661;
    }

    public void setIso31661(String iso31661)
    {
        this.iso31661 = iso31661;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getIso6391()
    {
        return iso6391;
    }

    public void setIso6391(String iso6391)
    {
        this.iso6391 = iso6391;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [site = "+site+", size = "+size+", iso31661 = "+ iso31661 +", name = "+name+", id = "+id+", type = "+type+", iso6391 = "+ iso6391 +", key = "+key+"]";
    }
}
			
			