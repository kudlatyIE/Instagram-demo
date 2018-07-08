Simple android app allows the user to log in to the account and displays basic statistics and recently added media.
Used:
retrofit,
picasso,
android ViewModel,
dagger 2

@author Nikodem Walicki

Importatnt:
Inside developer_storage.xml file you need to add your own valid clientID and redirected url (follows instagram API):

	<string name="insta_client_id">-your-client-ID-here-</string>
    <string name="redirect_url">https://-your-redirected-url-here-</string>

	
v1.1 
- Lauout update: user data added to recycle view (with own layout),
- Code refactore: modular architecture, implemented dagger 2,
- Added cache data.

v1.0 first release
