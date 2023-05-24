/**
 * The Address class creates address objects.
 * @author Ethan Newton
 * @version 1.0
 */
public class Address
{
    //Instance Variables
    private final String unitNumber;
    private final int    streetNumber;
    private final String streetName;
    private final String postalCode;
    private final String city;

    private static final int UNIT_NUMBER_MIN   = 1;
    private static final int UNIT_NUMBER_MAX   = 4;

    private static final int STREET_NUMBER_MIN = 0;
    private static final int STREET_NUMBER_MAX = 999999;

    private static final int STREET_NAME_MIN   = 1;
    private static final int STREET_NAME_MAX   = 20;

    private static final int POSTAL_CODE_MIN   = 5;
    private static final int POSTAL_CODE_MAX   = 6;

    private static final int CITY_NAME_MIN     = 1;
    private static final int CITY_NAME_MAX     = 30;



    /**
     * Constructor: Creates an address object.
     * @param unitNumber The unit number of the address.
     * @param streetNumber The street number of the address.
     * @param streetName The street name of the address.
     * @param postalCode The postal code of the address.
     * @param city The city of the address.
     */
    public Address(final String unitNumber,
                   final int streetNumber,
                   final String streetName,
                   final String postalCode,
                   final String city)
    {
        this.unitNumber = unitNumber;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.city = city;

        getExpectedExceptionsUnitNumber(unitNumber);
        getExpectedExceptionsStreetNumber(streetNumber);
        getExpectedExceptionsStreetName(streetName);
        getExpectedExceptionsPostalCode(postalCode);
        getExpectedExceptionsCity(city);
    }

    /**
     * This method returns the public version of the unit number.
     * @return The public version of the unit number.
     */
    public String getUnitNumber()
    {
        return unitNumber;
    }

    /**
     * This method returns the public version of the street number.
     * @return The public version of the street number.
     */
    public int getStreetNumber()
    {
        return streetNumber;
    }

    /**
     * This method returns the public version of the street name.
     * @return The public version of the street name.
     */
    public String getStreetName()
    {
        return streetName;
    }

    /**
     * This method returns the public version of the postal code.
     * @return The public version of the postal code.
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**
     * This method returns the public version of the city.
     * @return The public version of the city.
     */
    public String getCity()
    {
        return city;
    }

    /**
     * This method throws an IllegalArgumentException if the unit number is not null and not between 1-4 characters.
     * @param unitNumber The unit number to be tested.
     */
    public void getExpectedExceptionsUnitNumber(final String unitNumber)
    {
        if(unitNumber != null)
        {

            if(unitNumber.length() < UNIT_NUMBER_MIN     ||
                    unitNumber.length() > UNIT_NUMBER_MAX)
            {
                throw new IllegalArgumentException("Invalid unit number: " + unitNumber);
            }
        }
    }

    /**
     * This method throws an IllegalArgumentException if the street number is not between 0 and 999999.
     * @param streetNumber The street number to be tested.
     */
    public void getExpectedExceptionsStreetNumber(final int streetNumber)
    {
        if(streetNumber < STREET_NUMBER_MIN  ||
           streetNumber > STREET_NUMBER_MAX)
        {
            throw new IllegalArgumentException("Invalid street number: " + streetNumber);
        }
    }

    /**
     * This method throws an IllegalArgumentException if the street name is not between 1-20 characters.
     * It also throws a NullPointerException if the street name is null.
     * @param streetName The street name to be tested.
     */
    public void getExpectedExceptionsStreetName(final String streetName)
    {
        if(streetName != null)
        {
            if(streetName.length() < STREET_NAME_MIN      ||
                    streetName.length() > STREET_NAME_MAX)
            {
                throw new IllegalArgumentException("Invalid street name: " + streetName);
            }
        }
        else
        {
            throw new NullPointerException("Invalid street name: null");
        }
    }

    /**
     * This method throws an IllegalArgumentException if the postal code is not between 5-6 characters.
     * It also throws a NullPointerException if the postal code is null.
     * @param postalCode The postal code to be tested.
     */
    public void getExpectedExceptionsPostalCode(final String postalCode)
    {
        if(postalCode != null)
        {
            if(postalCode.length() < POSTAL_CODE_MIN      ||
                    postalCode.length() > POSTAL_CODE_MAX)
            {
                throw new IllegalArgumentException("Invalid postal code: " + postalCode);
            }
        }
        else
        {
            throw new NullPointerException("Invalid postal code: null");
        }

    }

    /**
     * This method throws an IllegalArgumentException if the city is not between 1-30 characters.
     * It also throws a NullPointerException if the city is null.
     * @param city The city to be tested.
     */
    public void getExpectedExceptionsCity(final String city)
    {
        if(city != null)
        {
            if(city.length() < CITY_NAME_MIN      ||
                    city.length() > CITY_NAME_MAX)
            {
                throw new IllegalArgumentException("Invalid city: " + city);
            }
        }
        else
        {
            throw new NullPointerException("Invalid city: null");
        }

    }
}
