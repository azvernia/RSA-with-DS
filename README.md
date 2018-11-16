# RSA-with-DS

Implementation of RSA Schema with digital signature


Run from KeyRing


Menu options are

1.) Create key

2.) Delete key

3.) Encrypt File

4.) Decrypt File

5.) Add Digital Signature

6.) Verify Digital Signature

7.) Exit



**Create key**

Generates a key and sets a userID and passphrase assiociated with that key


**Delete key**

Deletes the key associated with the entered userID. Requires passphrase


**Encrypt File**

Asks for ID of user and passphrase.

Then asks for filename and userID of recepient of the file

Encrypts file with public key of recepient


**Decrypt File**

Asks for ID of user, passphrase, and filename

Decrypts file with user's private key


**Add Digital Signature**

Asks for ID of user, passphrase, and filename

Generates a hash of the file and encrypts with user's private key

Attaches generated signature to the end of the file


**Verify Digital Signature**

Asks for ID of user, passphrase, filename, and userID of sender

Seperates digital signature from payload.

Decrypts signature with sender's public key.

Generates hash of the payload.

Compares hash of payload and decrypted signature, if equal seperate signature from file


**Exit**

Exits the program

