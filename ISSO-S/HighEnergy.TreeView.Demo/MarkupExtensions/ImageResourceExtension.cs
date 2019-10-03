using System;
using System.IO;
using System.Linq;
using System.Reflection;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace HighEnergy.TreeView.Demo.MarkupExtensions
{
    [ContentProperty("Source")]
    public class ImageResourceExtension : IMarkupExtension
    {
        public string Source { get; set; }

        public object ProvideValue(IServiceProvider serviceProvider)
        {
	        return Source == null ? null : ImageSource.FromResource(Source);
        }

        public static Stream GetEmbeddedResourceStream(Assembly assembly, string resourceFileName)
        {
            var resourceNames = assembly.GetManifestResourceNames();

            var resourcePaths = resourceNames
                .Where(x => x.EndsWith(resourceFileName, StringComparison.CurrentCultureIgnoreCase))
                .ToArray();

            if (!resourcePaths.Any())
                throw new Exception($"Resource ending with {resourceFileName} not found.");

            if (resourcePaths.Length > 1)
                throw new Exception(
		                $"Multiple resources ending with {resourceFileName} found: {Environment.NewLine}{string.Join(Environment.NewLine, resourcePaths)}");

            return assembly.GetManifestResourceStream(resourcePaths.Single());
        }
    }
}