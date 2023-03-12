using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using CommunityToolkit.Mvvm.ComponentModel;

namespace MauiSunmiPrinterTestApp21.ViewModel;
public partial class BaseViewModel : ObservableObject
{
    [ObservableProperty]
    string title;

}
