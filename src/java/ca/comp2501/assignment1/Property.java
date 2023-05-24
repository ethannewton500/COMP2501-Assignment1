package ca.comp2501.assignment1;

/**
 * The property class creates property objects
 * @author Ethan Newton
 * @version 1.0
 */
public class Property
{
    // Instance Variables
    private double        priceUsd;
    private final Address address;
    private final int     numberOfBedrooms;
    private final boolean swimmingPool;
    private final String  type_of_property;
    private final String  propertyId;

    private static final int PRICE_IN_USD_MIN       = 0;

    private static final int MIN_NUMBER_OF_BEDROOMS = 1;
    private static final int MAX_NUMBER_OF_BEDROOMS = 20;

    private static final int PROPERTY_ID_MIN        = 1;
    private static final int PROPERTY_ID_MAX        = 6;

    /**
     * Constructor: Creates property objects
     * @param priceUsd The price of the property.
     * @param address The address of the property.
     * @param numberOfBedrooms The number of bedrooms the property has.
     * @param swimmingPool True or false depending on if the property has a swimming pool.
     * @param type_of_property The type of property that property is. Must be either residence, commercial, or retail.
     * @param propertyId The ID of the property.
     */
    public Property(final double priceUsd,
                    final Address address,
                    final int numberOfBedrooms,
                    final boolean swimmingPool,
                    final String type_of_property,
                    final String propertyId)
    {
        this.priceUsd = priceUsd;
        this.address = address;
        this.numberOfBedrooms = numberOfBedrooms;
        this.swimmingPool = swimmingPool;
        this.type_of_property = type_of_property;
        this.propertyId = propertyId;

        getExpectedExceptionsPriceUsd();
        getExpectedExceptionsAddress();
        getExpectedExceptionsNumBedrooms();
        getExpectedExceptionsPropertyType();
        getExpectedExceptionsPropertyId();
    }

    /**
     * This method returns the public version of priceUsd.
     * @return The public version of priceUsd.
     */
    public double getPriceUsd()
    {
        return priceUsd;
    }

    /**
     * This method returns the public version of the address.
     * @return The public version of the address.
     */
    public Address getAddress()
    {
        return address;
    }

    /**
     * This method returns the public version of the number of bedrooms.
     * @return The public version of the number of bedrooms.
     */
    public int getNumberOfBedrooms()
    {
        return numberOfBedrooms;
    }

    /**
     * This method returns the public version of swimmingPool.
     * @return The public version of swimmingPool.
     */
    public boolean hasSwimmingPool()
    {
        return swimmingPool;
    }

    /**
     * This method returns the public version of the type of property.
     * @return The public version of the type of property.
     */
    public String getType()
    {
        return type_of_property;
    }

    /**
     * This method returns the public version of the propertyId
     * @return The public version of the propertyId
     */
    public String getPropertyId()
    {
        return propertyId;
    }

    /**
     * This method sets a new price in USD for the property. If the new price is lower than 0 an
     * IllegalArgumentException is thrown.
     * @param priceUsd The new price to be set.
     */
    public void setPriceUsd(final double priceUsd)
    {
        if(priceUsd < PRICE_IN_USD_MIN)
        {
            throw new IllegalArgumentException("Invalid price: " + priceUsd);
        }
        else
        {
            this.priceUsd = priceUsd;
        }
    }

    /**
     * This method throws an IllegalArgumentException if the priceUsd is lower than 0.
     */
    public void getExpectedExceptionsPriceUsd()
    {
        if(priceUsd < PRICE_IN_USD_MIN)
        {
            throw new IllegalArgumentException("Invalid price: " + priceUsd);
        }
    }

    /**
     * This method throws a NullPointerException if the address is null. It does not throw a IllegalArgumentException
     * because it uses an address object and all exceptions are already handled by the address class.
     */
    public void getExpectedExceptionsAddress()
    {
        if(address == null)
        {
            throw new NullPointerException("Invalid address: null");
        }
    }

    /**
     * This method throws an IllegalArgumentException if the number of bedrooms is not between 1-20.
     */
    public void getExpectedExceptionsNumBedrooms()
    {
        if(numberOfBedrooms < MIN_NUMBER_OF_BEDROOMS       ||
                numberOfBedrooms > MAX_NUMBER_OF_BEDROOMS)
        {
            throw new IllegalArgumentException("Invalid number of bedrooms: " + numberOfBedrooms);
        }
    }

    /**
     * This method throws an IllegalArgumentException if the property type is not one of the following 3 types:
     * residence, commercial, and retail. They are not case-sensitive. If property type is null, a
     * NullPointerException is thrown.
     */
    public void getExpectedExceptionsPropertyType()
    {
        if(type_of_property != null)
        {
            if(!type_of_property.equalsIgnoreCase("residence")       &&
                    !type_of_property.equalsIgnoreCase("commercial") &&
                    !type_of_property.equalsIgnoreCase("retail"))
            {
                throw new IllegalArgumentException("Invalid property type: " + type_of_property);
            }
        }
        else
        {
            throw new NullPointerException("Invalid property type: null");
        }
    }

    /**
     * This method throws an IllegalArgumentException if the propertyId is not between 1-6 characters.
     * If the propertyId is null, a NullPointerException is thrown.
     */
    private void getExpectedExceptionsPropertyId()
    {
        if(propertyId != null)
        {
            if(propertyId.length() < PROPERTY_ID_MIN       ||
                    propertyId.length() > PROPERTY_ID_MAX)
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
