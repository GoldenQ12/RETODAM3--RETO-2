package weatherPackage;

public interface Weather {
    String getLatitude();
    String getLongitude();
    String getGenerationtimeMs();
    String getUtcOffsetSeconds();
    String getTimezone();
    String getTimezoneAbbreviation();
    String getElevation();
    CurrentUnits getCurrentUnits();
    Current getCurrent();
    HourlyUnits getHourlyUnits();
    Hourly getHourly();
    DailyUnits getDailyUnits();
    Daily getDaily();
}
