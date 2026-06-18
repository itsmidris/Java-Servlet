## Quick Difference
| ServletConfig | ServletContext |
| ------------- | -------------- |
| Specific to one servlet | Shared by entire application |
| One object per servlet | One object per application |
| Used for servlet-specific config | Used for application-wide config |
| Accessed using getServletConfig() | Accessed using getServletContext() |

How many ServletContext objects are created?
One per web application

How many ServletConfig objects are created?
One per servlet

Which one is used for application-wide data?
ServletContext

Which one is used for servlet-specific configuration?
ServletConfig

Can ServletConfig access ServletContext?
Yes.
ServletContext context =
getServletConfig()
.getServletContext();

Interview Answer (2 Lines)
ServletConfig contains configuration information specific to a particular servlet.
ServletContext contains configuration and shared data available to all servlets in the application.