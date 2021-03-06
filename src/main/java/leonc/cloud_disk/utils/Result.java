package leonc.cloud_disk.utils;

public class Result
{
    private Integer code;
    private String message;
    private Object data;

    public void setMessageAndCode (String message, Integer code)
    {
        this.message = message;
        this.code = code;
    }

    public Integer getCode ()
    {
        return code;
    }

    public void setCode (Integer code)
    {
        this.code = code;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public Object getData ()
    {
        return data;
    }

    public void setData (Object data)
    {
        this.data = data;
    }
}