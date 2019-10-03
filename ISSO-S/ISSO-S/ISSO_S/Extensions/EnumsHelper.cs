using System;
using System.Linq;
using System.Reflection;
using CommonClassesLibrary;

namespace ISSO_S.iOS.Extensions
{
    public static class EnumsHelper
    {
        public static T GetAttributeOfType<T>(this Enum enumVal) where T : Attribute
        {
            var typeInfo = enumVal.GetType().GetTypeInfo();
            var v = typeInfo.DeclaredMembers.First(x => x.Name == enumVal.ToString());
            return v.GetCustomAttribute<T>();
        }

        public static string GetDescription(this Enum enumVal)
        {
            var attr = GetAttributeOfType<EnumDescriptionAttribute>(enumVal);
            return attr != null ? attr.Description : string.Empty;
        }

        public static T GetValueFromDescription<T>(string description)
        {
            var type = typeof(T);
            if (!type.IsEnum) throw new InvalidOperationException();
            foreach (var field in type.GetFields())
            {
	            if (Attribute.GetCustomAttribute(field,
		            typeof(EnumDescriptionAttribute)) is EnumDescriptionAttribute attribute)
                {
                    if (attribute.Description == description)
                        return (T)field.GetValue(null);
                }
                else
                {
                    if (field.Name == description)
                        return (T)field.GetValue(null);
                }
            }
            throw new ArgumentException("Not found.", nameof(description));
            // or return default(T);
        }
    }
}
