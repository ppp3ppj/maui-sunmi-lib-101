using MauiSunmiPrinterTestApp21.View;
using MauiSunmiPrinterTestApp21.ViewModel;
using Microsoft.Extensions.Logging;

namespace MauiSunmiPrinterTestApp21;

public static class MauiProgram
{
    public static MauiApp CreateMauiApp()
    {
        var builder = MauiApp.CreateBuilder();
        builder
            .UseMauiApp<App>()
            .ConfigureFonts(fonts =>
            {
                fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
                fonts.AddFont("OpenSans-Semibold.ttf", "OpenSansSemibold");
            });

#if DEBUG
        builder.Logging.AddDebug();
#endif

        // Add Singleton DI
        builder.Services.AddSingleton<MainPage>();
        builder.Services.AddSingleton<SunmiPrinterViewModel>();

        return builder.Build();
    }
}
