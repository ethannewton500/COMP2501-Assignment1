package ca.comp2501.assignment1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Agency class is used for creating agency objects.
 * @author Ethan Newton
 * @version 1.0
 */
public class Agency
{
    // Instance Variables
    private final String agencyName;
    private final Map<String, Property> properties;

    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 30;

    /**
     * Constructor: Creates agency objects.
     * @param name The name of the agency.
     */
    public Agency(final String name)
    {
        if(name == null || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
        {
            throw new IllegalArgumentException("Invalid agency");
        }
        else
        {
            this.agencyName = name;
            properties = new HashMap<>();
        }
    }

    /**
     * This method adds a property to the properties' hashmap.
     * @param property The property to be added.
     */
    public void addProperty(final Property property)
    {
        if(property.getPropertyId() != null)
        {
            properties.put(property.getPropertyId(), property);
        }
    }

    /**
     * This method removes a property from properties hashmap using the propertyId.
     * @param propertyId The property id that will be used to remove the property.
     */
    public void removeProperty(final String propertyId)
    {
        properties.remove(propertyId);
    }

    /**
     * This method returns the public version of a property from properties using the propertyId.
     * @param propertyId The property id to be used to return the associated property
     * @return The property that matches the propertyId
     */
    public Property getProperty(final String propertyId)
    {
        return properties.get(propertyId);
    }

    /**
     * This method uses a for each loop to go through all the properties in properties and adds the value of each
     * property to a new double variable called totalPropertyValues. After the method has looped through all the
     * properties, totalPropertyValues is returned.
     * @return The sum of all the property values.
     */
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

    /**
     * This method uses a for each loop to go through all the properties in properties and adds all properties that
     * have a pool to a new Arraylist called propertiesWithPools. Once the method loops through all the properties,
     * propertiesWithPools is returned.
     * @return The Arraylist of properties with pools
     */
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

    /**
     * This method uses a for each loop to go through all the properties in properties and adds all the properties
     * that have priceUsd between minUsd and maxUsd to an array. Once the method loops through all the properties,
     * the array is returned.
     * @param minUsd The min price to search for properties.
     * @param maxUsd The max price to search for properties.
     * @return The array of properties.
     */
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

    /**
     * This method loops through all the properties in properties using a foreach loop and adds all the properties
     * that are on streetName to a new arraylist. Once the method loops through all the properties the arraylist is
     * returned.
     * @param streetName The street name that will be used to match properties.
     * @return The propertiesOn arraylist.
     */
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

    /**
     * This method loops through all the properties in properties using a for each loop and adds all the properties
     * that have a number of bedrooms between minBedrooms and maxBedrooms to a hashmap. The key is the property id
     * and the value is the property.
     * @param minBedrooms The min number of bedrooms to search between.
     * @param maxBedrooms The max number of bedrooms to search between.
     * @return The hashmap of the properties .
     */
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

    /**
     * This method loops through all the properties in properties using a for each loop. Every property that matches
     * the propertyType parameter is added to the array list in the form of a string using the following format:
     * "(entry number)) Property (property id): (unit #(unit number{ if applicable})) at (street number) (street name)
     * (postal code) in (city) ((num of bedrooms) bedroom(s {if applicable}) (plus pool {if applicable}): $(priceUsd)."
     * Once all the properties have been added, this method returns the array list.
     * @param propertyType The type of property that the array list will contain.
     * @return The completed array list.
     */
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

    /**
     * This method takes in a string and then splits it at every space and capitalizes the first letter of each word.
     * @param originalString The original string to be capitalized.
     * @return The formatted string.
     */
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
