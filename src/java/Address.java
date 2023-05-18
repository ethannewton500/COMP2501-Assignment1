public class Address
{
    //Instance Variables
    private final String unitNumber;
    private final int streetNumber;
    private final String streetName;
    private final String postalCode;
    private final String city;

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

    public String getUnitNumber()
    {
        return unitNumber;
    }

    public int getStreetNumber()
    {
        return streetNumber;
    }

    public String getStreetName()
    {
        return streetName;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public String getCity()
    {
        return city;
    }

    public void getExpectedExceptionsUnitNumber(final String unitNumber)
    {
        if(unitNumber != null)
        {
            if(unitNumber.isEmpty())
            {
                throw new IllegalArgumentException("Invalid unit number: ");
            }
            else if(unitNumber.length() > 4)
            {
                throw new IllegalArgumentException("Invalid unit number: " + unitNumber);
            }
        }
    }

    public void getExpectedExceptionsStreetNumber(final int streetNumber)
    {
        if(streetNumber < 0 ||
           streetNumber > 999999)
        {
            throw new IllegalArgumentException("Invalid street number: " + streetNumber);
        }
    }

    public void getExpectedExceptionsStreetName(final String streetName)
    {
        if(streetName != null)
        {
            if(streetName.isEmpty())
            {
                throw new IllegalArgumentException("Invalid street name: ");
            }
            else if(streetName.length() > 20)
            {
                throw new IllegalArgumentException("Invalid street name: " + streetName);
            }
        }
        else
        {
            throw new NullPointerException("Invalid street name: null");
        }
    }

    public void getExpectedExceptionsPostalCode(final String postalCode)
    {
        if(postalCode != null)
        {
            if(postalCode.length() < 5 || postalCode.length() > 6)
            {
                throw new IllegalArgumentException("Invalid postal code: " + postalCode);
            }
        }
        else
        {
            throw new NullPointerException("Invalid postal code: null");
        }

    }

    public void getExpectedExceptionsCity(final String city)
    {
        if(city != null)
        {
            if(city.length() < 1 ||
                    city.length() > 30)
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
