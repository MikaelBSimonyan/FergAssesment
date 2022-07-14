*The assessment was reasonably challenging, but fun to complete.
*First off, I ran into WebDriverException:
WebDriverException: unknown error: cannot determine loading status
from unknown error: unexpected command response.
This was largely due to using a personal mac, with the auto-update future "on"
for chrome. 
The issue was found connected to ChromeDriver 103.0.5060.24.
I had to manually prevent chrome from aggressively auto-updating, by 
modifying "com.google.Keystone.plist". No issues on ChromeDriver 102.
I've added the missing line for FirefoxDriver setup initiation in the BaseFramework.
3 times out of 5 I had successful execution of all 4 test cases on Firefox, 
Haven't worked with firefox, so that's definitely an area for me to get better at.
Few "thread.sleep" statements have been used, which I'm not very proud of. With more time on my hands
to study the project more, I'm sure I would be able to eliminate them all together.
As far as Appium goes, I played around with it a couple of times but that's about it , would eventually figure out how to make tests run,
but this would definitely take me more time to accomplish. Would love and am willing to learn it though.

         Thanks for the opportunity.

