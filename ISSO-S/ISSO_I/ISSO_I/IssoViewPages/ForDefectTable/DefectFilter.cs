using System;

namespace ISSO_I.IssoViewPages
{
    public class DefectFilter : ICloneable
    {
        public int ConstrId { get; set; }
        public string Description { get; set; }
        public bool Activated { get; set; }

        public DefectFilter() { }

        public DefectFilter(int constrId, string description, bool activated)
        {
            ConstrId = constrId;
            Description = description;
            Activated = activated;
        }

        public object Clone()
        {
            return MemberwiseClone();
        }
    }
}
