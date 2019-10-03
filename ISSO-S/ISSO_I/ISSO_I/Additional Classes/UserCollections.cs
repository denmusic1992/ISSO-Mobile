using System;
using CommonClassesLibrary;

namespace ISSO_I.Additional_Classes
{
    public class UserCollections
    {
        public string Name { get; set; }

        public string Description { get; set; }

        public string DateCreate { get; set; }

        public string DateModify { get; set; }

        public int IssoCount { get; set; }

        public bool IsChecked { get; set; }

        public static UserCollections Convert(DBHelper.ListCollection collection)
        {
            var userCollections = new UserCollections
            {
                Name = collection.CollectionName,
                Description = collection.Description,
                DateCreate = new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(collection.DateCreate).ToString("dd.MM.yyyy"),
                DateModify = new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc).AddMilliseconds(collection.DateModify).ToString("dd.MM.yyyy"),
                IssoCount = collection.IssoList.Split(',').Length,
                IsChecked = false
            };
            return userCollections;
        }
    }
}
