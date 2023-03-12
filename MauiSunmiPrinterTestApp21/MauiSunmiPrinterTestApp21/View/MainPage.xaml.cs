using MauiSunmiPrinterTestApp21.ViewModel;

namespace MauiSunmiPrinterTestApp21.View;

public partial class MainPage : ContentPage
{
    public MainPage(SunmiPrinterViewModel SunmiVm)
    {
        InitializeComponent();
        BindingContext = SunmiVm;
    }
}

