package com.ahold.model.collectiondetails;

import com.ahold.model.common.WebImage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArtObjectDetail {

    public CollectionDetailsLinks links;
    public String id;
    public String priref;
    public String objectNumber;
    public String language;
    public String title;
    public String copyrightHolder;
    public WebImage webImage;
    public List<Color> colors;
    public List<ColorsWithNormalization> colorsWithNormalization;
    public List<NormalizedColor> normalizedColors;
    public List<Normalized32Color> normalized32Colors;
    public List<String> materialsThesaurus;
    public List<String> techniquesThesaurus;
    public List<String> productionPlacesThesaurus;
    public List<String> titles;
    public String description;
    public String labelText;
    public List<String> objectTypes;
    public List<String> objectCollection;
    public List<String> makers;
    public List<PrincipalMaker> principalMakers;
    public String plaqueDescriptionDutch;
    public String plaqueDescriptionEnglish;
    public String principalMaker;
    public String artistRole;
    public List<String> associations;
    public Acquisition acquisition;
    public List<String> exhibitions;
    public List<String> materials;
    public List<String> techniques;
    public List<String> productionPlaces;
    public Dating dating;
    public Classification classification;
    public Boolean hasImage;
    public List<String> historicalPersons;
    public List<String> inscriptions;
    public List<String> documentation;
    public List<String> catRefRPK;
    public String principalOrFirstMaker;
    public List<Dimension> dimensions;
    public List<String> physicalProperties;
    public String physicalMedium;
    public String longTitle;
    public String subTitle;
    public String scLabelLine;
    public Label label;
    public Boolean showImage;
    public String location;
}
