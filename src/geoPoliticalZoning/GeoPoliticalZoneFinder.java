package geoPoliticalZoning;

public class GeoPoliticalZoneFinder {
    public GeoPoliticalZoneFinder() {
    }

    public GeoPoliticalZone findZone(String state) {
        switch (state.toLowerCase()) {
            case "benue":
            case "fct":
            case "kogi":
            case "kwara":
            case "nasarawa":
            case "niger":
            case "plateau":
                return GeoPoliticalZone.NORTH_CENTRAL;
            case "adamawa":
            case "bauchi":
            case "borno":
            case "gombe":
            case "taraba":
            case "yobe":
                return GeoPoliticalZone.NORTH_EAST;
            case "kaduna":
            case "katsina":
            case "kano":
            case "kebbi":
            case "sokoto":
            case "jigawa":
            case "zamfara":
                return GeoPoliticalZone.NORTH_WEST;
            case "abia":
            case "anambra":
            case "ebonyi":
            case "enugu":
            case "imo":
                return GeoPoliticalZone.SOUTH_EAST;
            case "akwa-ibom":
            case "bayelsa":
            case "cross-river":
            case "delta":
            case "edo":
            case "rivers":
                return GeoPoliticalZone.SOUTH_SOUTH;
            case "ekiti":
            case "lagos":
            case "osun":
            case "ondo":
            case "ogun":
            case "oyo":
                return GeoPoliticalZone.SOUTH_WEST;
            default:
                return null;
        }
    }
}
