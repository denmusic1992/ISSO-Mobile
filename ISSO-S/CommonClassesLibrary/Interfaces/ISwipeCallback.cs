namespace CommonClassesLibrary.Interfaces
{
    public interface ISwipeCallback
    {
	    /// <summary>
	    /// Если смахивание происходит влево
	    /// </summary>
	    void OnLeftSwipe();

	    /// <summary>
	    /// Если смахивание происходит вправо
	    /// </summary>
	    void OnRightSwipe();
        ///// <summary>
        ///// Если смахивание не происходит
        ///// </summary>
        ///// <param name="view"></param>
        //void OnNothingSwiped(View view);
    }

    public enum SwipeDirections
    {
        /// <summary>
        /// Swipe влево
        /// </summary>
        LeftSwipe = 0,
        /// <summary>
        /// Swipe вправо
        /// </summary>
        RightSwipe = 1
    }
}
