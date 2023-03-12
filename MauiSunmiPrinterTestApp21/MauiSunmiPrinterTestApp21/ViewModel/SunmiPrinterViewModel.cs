using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


using CommunityToolkit.Mvvm.ComponentModel;

using Com.Ppp.Sunmit2.Services;
using Com.Ppp.Sunmit2.Contracts;

using Android.Content; // Context
using CommunityToolkit.Mvvm.Input;

namespace MauiSunmiPrinterTestApp21.ViewModel;
public partial class SunmiPrinterViewModel : ObservableObject
{
    Context context = Android.App.Application.Context;

    private SunmiPrinter sunmi = SunmiPrinter.Instance;

    [ObservableProperty]
    private string printerModel;

    [ObservableProperty]
    private string printerSerialNo;

    public SunmiPrinterViewModel()
    {
        //SunmiPrinter.Instance.InitPrinter(context);
        Debug.WriteLine("HII This view model");
        sunmi.InitPrinter(context);
        PrinterModel = sunmi.PrinterModal;
        PrinterSerialNo = sunmi.PrinterSerialNo;

        //   Debug.WriteLine($"HII This view model {context}");
    }

    [RelayCommand]
    public void PrintText()
    {
        string text = "Test Print";
        Debug.WriteLine("Test Print Command");
        sunmi.PrintText(text, null);
    }
}
