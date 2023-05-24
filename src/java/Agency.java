import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Agency
{
    // Instance Variables
    private final String name;
    private final Map<String, Property> properties;

    public Agency(final String name)
    {
        if(name == null || name.length() < 1 || name.length() > 30)
        {
            throw new IllegalArgumentException("Invalid agency");
        }
        else
        {
            this.name = name;
            properties = new HashMap<>();
        }

    }
    public void addProperty(final Property property)
    {
        if(property.getPropertyId() != null)
        {
            properties.put(property.getPropertyId(), property);
        }
    }

    public void removeProperty(final String propertyId)
    {
        properties.remove(propertyId);
    }

    public Property getProperty(final String propertyId)
    {
        return properties.get(propertyId);
    }

    public double getTotalPropertyValues()
    {
        double totalPropertyValues;
        totalPropertyValues = 0.0;

        for(Property property : properties.values())
        {
            totalPropertyValues += property.getPriceUsd();
        }
        return totalPropertyValues;
    }
    public List<Property> getPropertiesWithPools()
    {
        List<Property> propertiesWithPools;
        propertiesWithPools = new ArrayList<>();

        for(Property property : properties.values())
        {
            if(property.hasSwimmingPool())
            {
                propertiesWithPools.add(property);
            }
        }
        return propertiesWithPools;
    }

    public Property[] getPropertiesBetween(final double minUsd, final double maxUsd)
    {
        int sizeOfArray = 0;

        for(Property property : properties.values())
        {
            if(property.getPriceUsd() >= minUsd && property.getPriceUsd() <= maxUsd)
            {
                sizeOfArray += 1;
            }
        }

        if(sizeOfArray == 0)
        {
            return null;
        }


        Property[] propertiesBetween;
        propertiesBetween = new Property[sizeOfArray];
        int index = 0;

        for(Property property : properties.values())
        {
            if(property.getPriceUsd() >= minUsd && property.getPriceUsd() <= maxUsd)
            {
                propertiesBetween[index] = property;
                index += 1;
            }
        }
        return propertiesBetween;
    }

    public List<Address> getPropertiesOn(final String streetName)
    {
        List<Address> propertiesOn;
        propertiesOn = new ArrayList<>();
        for(Property property : properties.values())
        {
            if(property.getAddress().getStreetName().equals(streetName))
            {
                propertiesOn.add(property.getAddress());
            }
        }
        if(propertiesOn.isEmpty())
        {
            return null;
        }
        return propertiesOn;
    }

    public HashMap<String, Property> getPropertiesWithBedrooms(final int minBedrooms, final int maxBedrooms)
    {
        HashMap<String, Property> propertiesWithBedrooms;
        propertiesWithBedrooms = new HashMap<>();
        for(Property property : properties.values())
        {
            if(property.getNumberOfBedrooms() >= minBedrooms && property.getNumberOfBedrooms() <= maxBedrooms)
            {
                propertiesWithBedrooms.put(property.getPropertyId(), property);
            }
        }

        if(propertiesWithBedrooms.isEmpty())
        {
            return null;
        }
        return propertiesWithBedrooms;
    }

    public ArrayList<String> getPropertiesOfType(final String propertyType)
    {
        ArrayList<String> propertiesOfType;
        propertiesOfType = new ArrayList<>();

        int index;
        index = 1;
        propertiesOfType.add("Type: " + propertyType.toUpperCase() + "\n");

        for(Property property : properties.values())
        {
            String streetNameAdjusted, postalCodeAdjusted, cityAdjusted;
            streetNameAdjusted = capitalizeWords(property.getAddress().getStreetName());
            postalCodeAdjusted = property.getAddress().getPostalCode().toUpperCase();
            cityAdjusted = capitalizeWords(property.getAddress().getCity());

            if(property.getType().equalsIgnoreCase(propertyType) && property.getAddress().getUnitNumber() == null)
            {
                // 1 Bedroom plus Pool
                if(property.getNumberOfBedrooms() == 1 && property.hasSwimmingPool())
                {
                    propertiesOfType.add(index + ") Property " + property.getPropertyId() + ": " + property.getAddress().getStreetNumber() + " " + streetNameAdjusted + postalCodeAdjusted + " in " + cityAdjusted  + "(" + property.getNumberOfBedrooms()  + " bedroom plus pool): $" + String.format("%.0f", property.getPriceUsd()) + ".\n");
                }
                // 1 Bedroom no Pool
                else if(property.getNumberOfBedrooms() == 1 && !property.hasSwimmingPool())
                {
                    propertiesOfType.add(index + ") Property " + property.getPropertyId() + ": " + property.getAddress().getStreetNumber() + " " + streetNameAdjusted + postalCodeAdjusted + " in " + cityAdjusted  + "(" + property.getNumberOfBedrooms()  + " bedroom): $" + String.format("%.0f", property.getPriceUsd()) + ".\n");
                }
                // Multi Bedroom plus pool
                else if(property.getNumberOfBedrooms() > 1 && property.hasSwimmingPool())
                {
                    propertiesOfType.add(index + ") Property " + property.getPropertyId() + ": " + property.getAddress().getStreetNumber() + " " + streetNameAdjusted + postalCodeAdjusted + " in " + cityAdjusted  + "(" + property.getNumberOfBedrooms()  + " bedrooms plus pool): $" + String.format("%.0f", property.getPriceUsd()) + ".\n");
                }
                else if(property.getNumberOfBedrooms() > 1 && !property.hasSwimmingPool())
                {
                    // Multi Bedroom no pool
                    propertiesOfType.add(index + ") Property " + property.getPropertyId() + ": " + property.getAddress().getStreetNumber() + " " + streetNameAdjusted + postalCodeAdjusted + " in " + cityAdjusted  + "(" + property.getNumberOfBedrooms()  + " bedrooms): $" + String.format("%.0f", property.getPriceUsd()) + ".\n");
                }

            }
            else if(property.getType().equalsIgnoreCase(propertyType) && property.getAddress().getUnitNumber() != null)
            {
                // 1 Bedroom plus Pool
                if(property.getNumberOfBedrooms() == 1 && property.hasSwimmingPool())
                {
                    propertiesOfType.add(index + ") Property " + property.getPropertyId() + ": unit #" + property.getAddress().getUnitNumber() + " at " + property.getAddress().getStreetNumber() + " " + streetNameAdjusted + postalCodeAdjusted + " in " + cityAdjusted  + "(" + property.getNumberOfBedrooms()  + " bedroom plus pool): $" + String.format("%.0f", property.getPriceUsd()) + ".\n");
                }
                // 1 Bedroom no Pool
                else if(property.getNumberOfBedrooms() == 1 && !property.hasSwimmingPool())
                {
                    propertiesOfType.add(index + ") Property " + property.getPropertyId() + ": unit #" + property.getAddress().getUnitNumber() + " at " + property.getAddress().getStreetNumber() + " " + streetNameAdjusted + postalCodeAdjusted + " in " + cityAdjusted  + "(" + property.getNumberOfBedrooms()  + " bedroom): $" + String.format("%.0f", property.getPriceUsd()) + ".\n");
                }
                // Multi Bedroom plus pool
                else if(property.getNumberOfBedrooms() > 1 && property.hasSwimmingPool())
                {
                    propertiesOfType.add(index + ") Property " + property.getPropertyId() + ": unit #" + property.getAddress().getUnitNumber() + " at " + property.getAddress().getStreetNumber() + " " + streetNameAdjusted + postalCodeAdjusted + " in " + cityAdjusted  + "(" + property.getNumberOfBedrooms()  + " bedrooms plus pool): $" + String.format("%.0f", property.getPriceUsd()) + ".\n");
                }
                else if(property.getNumberOfBedrooms() > 1 && !property.hasSwimmingPool())
                {
                    // Multi Bedroom no pool
                    propertiesOfType.add(index + ") Property " + property.getPropertyId() + ": unit #" + property.getAddress().getUnitNumber() + " at " + property.getAddress().getStreetNumber() + " " + streetNameAdjusted + postalCodeAdjusted + " in " + cityAdjusted  + "(" + property.getNumberOfBedrooms()  + " bedrooms): $" + String.format("%.0f", property.getPriceUsd()) + ".\n");
                }

            }
        }
        if(propertiesOfType.size() <= 1)
        {
            propertiesOfType.add("<none found>");
            return propertiesOfType;
        }
        return propertiesOfType;
    }

    public String capitalizeWords(final String originalString)
    {
        String[] words = originalString.split(" ");
        String finalString = "";


        for(String word : words)
        {
            if(!word.isEmpty())
            {
                char firstLetter = Character.toUpperCase(word.charAt(0));
                String capitalString = firstLetter + word.substring(1) + " ";
                finalString = finalString + capitalString;
            }
        }
        return finalString;
    }
}
