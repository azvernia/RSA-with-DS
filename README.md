# RSA-with-DS
Implementation of RSA Schema with digital signature

Run from KeyRing
\n
Menu options are
1.) Create key\n
2.) Delete key\n
3.) Encrypt File\n
4.) Decrypt File\n
5.) Add Digital Signature\n
6.) Verify Digital Signature\n
7.) Exit\n
\n
Create key
Generates a key and sets a userID and passphrase assiociated with that key
\n
Delete key
Deletes the key associated with the entered userID. Requires passphrase
\n
Encrypt File
Asks for ID of user and passphrase.\n
Then asks for filename and userID of recepient of the file\n
Encrypts file with public key of recepient\n
\n
Decrypt File\n
Asks for ID of user, passphrase, and filename\n
Decrypts file with user's private key\n
\n
Add Digital Signature\n
Asks for ID of user, passphrase, and filename\n
Generates a hash of the file and encrypts with user's private key\n
Attaches generated signature to the end of the file\n
\n
Verify Digital Signature\n
Asks for ID of user, passphrase, filename, and userID of sender\n
Seperates digital signature from payload.\n
Decrypts signature with sender's public key.\n
Generates hash of the payload.\n
Compares hash of payload and decrypted signature, if equal seperate signature from file\n
\n
Exit\n
Exits the program\n

