Servlet Created
↓
init()      → once
↓
service()   → many times
↓
destroy()   → once
<!--------------->
Tomcat
↓
new LoginServlet()
↓
init()
↓
service()
↓
doGet()/doPost()
↓
service()
↓
doGet()/doPost()
↓
destroy()
<!--------------->
Servlet lifecycle = Constructor → init() → service() → doGet()/doPost() → destroy(), where init() and destroy() run once, but doGet()/doPost() run for every request.

Easy Way To Remember
Server Starts
↓
Constructor
↓
init()       (once)
↓
doGet()/doPost()  (many times)
↓
destroy()    (once)
↓
Server Stops