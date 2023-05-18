

public class Property
{
    // Instance Variables
    private double priceUsd;
    private final Address address;
    private final int numberOfBedrooms;
    private final boolean swimmingPool;
    private final String type;
    private final String propertyId;

    public Property(final double priceUsd,
                    final Address address,
                    final int numberOfBedrooms,
                    final boolean swimmingPool,
                    final String type,
                    final String propertyId)
    {
        this.priceUsd = priceUsd;
        this.address = address;
        this.numberOfBedrooms = numberOfBedrooms;
        this.swimmingPool = swimmingPool;
        this.type = type;
        this.propertyId = propertyId;

        getExpectedExceptionsPriceUsd();
        getExpectedExceptionsAddress();
        getExpectedExceptionsNumBedrooms();
        getExpectedExceptionsPropertyType();
        getExpectedExceptionsPropertyId();
    }

    public double getPriceUsd()
    {
        return priceUsd;
    }

    public Address getAddress()
    {
        return address;
    }

    public int getNumberOfBedrooms()
    {
        return numberOfBedrooms;
    }

    public boolean hasSwimmingPool()
    {
        return swimmingPool;
    }

    public String getType()
    {
        return type;
    }

    public String getPropertyId()
    {
        return propertyId;
    }

    public void setPriceUsd(final double priceUsd)
    {
        this.priceUsd = priceUsd;
    }

    public void getExpectedExceptionsPriceUsd()
    {
        if(priceUsd < 0)
        {
            throw new IllegalArgumentException("Invalid price: " + priceUsd);
        }
    }

    public void getExpectedExceptionsAddress()
    {
        if(address == null)
        {
            throw new NullPointerException("Invalid address: null");
        }
    }

    public void getExpectedExceptionsNumBedrooms()
    {
        if(numberOfBedrooms < 1 || numberOfBedrooms > 20)
        {
            throw new IllegalArgumentException("Invalid number of bedrooms: " + numberOfBedrooms);
        }
    }

    public void getExpectedExceptionsPropertyType()
    {
        if(type != null)
        {
            if(!type.equalsIgnoreCase("residence")       &&
                    !type.equalsIgnoreCase("commercial") &&
                    !type.equalsIgnoreCase("retail"))
            {
                throw new IllegalArgumentException("Invalid property type: " + type);
            }
        }
        else
        {
            throw new NullPointerException("Invalid property type: null");
        }
    }

    private void getExpectedExceptionsPropertyId()
    {
        if(propertyId != null)
        {
            if(propertyId.length() < 1 || propertyId.length() > 6)
            {
                throw new IllegalArgumentException("Invalid property id: " + propertyId);
            }
        }
        else
        {
            throw new NullPointerException("Invalid property id: null");
        }
    }
}
