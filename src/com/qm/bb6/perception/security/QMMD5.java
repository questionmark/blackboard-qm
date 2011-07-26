/*
 * derived from the RSA Data Security, Inc. MD5 Message-Digest Algorithm *
 * See copyright notice in header file *
 * All changes to original code copyright (c) Question Mark Computing Ltd 1997 *

File ID			: MD5.cpp
Date created	: 18.3.97
Description		: MD5 hash code routine
Last modified	: 18.2.97
					Changed lines 127 and 149 to limit output to chars '0' to '9'


*/

package com.qm.bb6.perception.security;

import java.io.UnsupportedEncodingException;

public class QMMD5 {

	public static final int DIGEST_LENGTH = 16;  // *** do NOT change ! ***

	private MD5_CTX mdContext;
	private int[] in;


	private static byte[] PADDING = new byte[]{
												(byte) 0x80, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
												(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
												(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
												(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 
												(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 
												(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 
												(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 
												(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 
												(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 
												(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, 
												(byte) 0, (byte) 0, (byte) 0, (byte) 0
   											 } ;

	
	private static class MD5_CTX {
		int[] i = new int[2];                   				/* number of _bits_ handled mod 2^64 */
		int[] buf = new int[4];								/* scratch buffer */
		byte[] in = new byte[64];                    			/* input buffer */
		byte[] digest = new byte[DIGEST_LENGTH];     			/* actual digest after MD5Final call */
	}
	
	
	// QM4 server specific interface
	
	public QMMD5() {
	
		mdContext = new MD5_CTX();
		in = new int[DIGEST_LENGTH];
	
	}
	
	
	
	/*
	 ***********************************************************************
	 **  Message-digest routines:                                         **
	 **  To form the message digest for a message M                       **
	 **    (1) Initialize a context buffer mdContext using MD5Init        **
	 **    (2) Call MD5Update on mdContext and M                          **
	 **    (3) Call MD5Final on mdContext                                 **
	 **  The message digest is now in mdContext->digest[0...DIGEST_LENGTH-1]           **
	 ***********************************************************************
	 */
	
	/* forward declaration */
	
	/* F, G, H and I are basic MD5 functions */
	//define F(x, y, z) (((x) & (y)) | ((~x) & (z)))
	//define G(x, y, z) (((x) & (z)) | ((y) & (~z)))
	//define H(x, y, z) ((x) ^ (y) ^ (z))
	//define I(x, y, z) ((y) ^ ((x) | (~z)))
	
	private int F(int x, int y, int z) { return ((x & y) | ((~x) & z)); };
	private int G(int x, int y, int z) { return ((x & z) | (y & (~z))); };
	private int H(int x, int y, int z) { return (x ^ y ^ z); };
	private int I(int x, int y, int z) { return (y ^ (x | ~z)); };
	
	/* ROTATE_LEFT rotates x left n bits */
	//define ROTATE_LEFT(x, n) (((x) << (n)) | ((x) >> (32-(n))))
	private int ROTATE_LEFT( int x, int n ){
		return ((x << n) | (x >>> (32-n)));
	}
	
	
	
	/* FF, GG, HH, and II transformations for rounds 1, 2, 3, and 4 */
	/* Rotation is separate from addition to prevent recomputation */
	/*
	#define FF(a, b, c, d, x, s, ac) \
		{(a) += F ((b), (c), (d)) + (x) + (int)(ac); \
		 (a) = ROTATE_LEFT ((a), (s)); \
		 (a) += (b); \
		}
	#define GG(a, b, c, d, x, s, ac) \
		{(a) += G ((b), (c), (d)) + (x) + (int)(ac); \
		 (a) = ROTATE_LEFT ((a), (s)); \
		 (a) += (b); \
		}
	#define HH(a, b, c, d, x, s, ac) \
		{(a) += H ((b), (c), (d)) + (x) + (int)(ac); \
		 (a) = ROTATE_LEFT ((a), (s)); \
		 (a) += (b); \
		}
	#define II(a, b, c, d, x, s, ac) \
		{(a) += I ((b), (c), (d)) + (x) + (int)(ac); \
		 (a) = ROTATE_LEFT ((a), (s)); \
		 (a) += (b); \
		}
	*/
	
	private int FF( int a, int b, int c, int d, int x, int s, int ac ){
		a += (F(b, c, d) + x + ac) ;
		a = ROTATE_LEFT(a, s) ;
		a += b ;
		return a ;
	}
	
	private int GG( int a, int b, int c, int d, int x, int s, int ac ){
		a += (G(b, c, d) + x + ac) ;
		a = ROTATE_LEFT(a, s) ;
		a += b ;
		return a ;
	}
	
	private int HH( int a, int b, int c, int d, int x, int s, int ac ){
		a += (H(b, c, d) + x + ac) ;
		a = ROTATE_LEFT(a, s) ;
		a += b ;
		return a ;
	}

	private int II( int a, int b, int c, int d, int x, int s, int ac ){
		a += (I(b, c, d) + x + ac) ;
		a = ROTATE_LEFT(a, s) ;
		a += b ;
		return a;
	}
			
	/*******************************/
	/* calculate the MD5 of data */
	/*******************************/
	
	private byte[] MD5(byte[] data, int bytes ){
		
		byte[] hash = new byte[DIGEST_LENGTH];
	
		MD5Init ();
	 	MD5Update( data,bytes );
		MD5Final ();
	
		for(int i=0; i<DIGEST_LENGTH; i++) {
			// limit digest to characters '0' to '9'
			hash[i] = (byte) ((mdContext.digest[i] % 10) + '0');
			mdContext.digest[i]=0;											/* zero it for secrecy */
		}
	
		swap_bytes( hash, 0 );
		swap_bytes( hash, 4 );
		swap_bytes( hash, 8 );
		swap_bytes( hash, 12 );
		
		return hash;
	}
	
	
	/* The routine MD5Init initializes the message-digest context
		 mdContext. All fields are set to zero.
	 */
	private void MD5Init(){
		
		mdContext.i[0] = mdContext.i[1] = (int) 0;
	
		/* Load magic initialization constants.
		 */
	
		mdContext.buf[0] = 0x67452301;
		mdContext.buf[1] = 0xefcdab89;
		mdContext.buf[2] = 0x98badcfe;
		mdContext.buf[3] = 0x10325476;
	}
	
	/* The routine MD5Update updates the message-digest context to
		 account for the presence of each of the characters inBuf[0..inLen-1]
		 in the message whose digest is being computed.
	 */
	
	private void MD5Update( byte[] inBuf, int inLen ){
		int mdi;
		int i = 0;
		int ii;
	
		/* compute number of bytes mod 64 */	
		mdi = ((int) (mdContext.i[0] >> 3)) & 0x3F;
		

		/* update number of bits */
		
		if ((mdContext.i[0] + ((int)inLen << 3)) < mdContext.i[0])
		mdContext.i[1]++;
		
		mdContext.i[0] += ((int)inLen << 3);
		mdContext.i[1] += ((int)inLen >> 29);
		
		while ( inLen > 0 ){
			inLen--;
		
			/* add new character to buffer, increment mdi */
			
			mdContext.in[mdi++] = inBuf[mdi];
			
			/* transform if necessary */
			
			if ( mdi == 64 ) {
				for (i = 0, ii = 0; i < 16; i++, ii += 4)
					in[i] = (((int)mdContext.in[ii+3]) << 24) |
									(((int)mdContext.in[ii+2]) << 16) |
									(((int)mdContext.in[ii+1]) << 8) |
									((int)mdContext.in[ii]);
				Transform ();
				mdi = 0;
			}
		}
	}
		
	/* The routine MD5Final terminates the message-digest computation and
		 ends with the desired message digest in mdContext.digest[0...15].
	 */
	
	private void MD5Final(){
		
		int mdi;
		int i, ii;
		int padLen;
	
		/* save number of bits */
	
		in[14] = mdContext.i[0];
		in[15] = mdContext.i[1];
	
		/* compute number of bytes mod 64 */
	
		mdi = ((int) (mdContext.i[0] >> 3)) & 0x3F;
	
		/* pad out to 56 mod 64 */
	
		padLen = (mdi < 56) ? (56 - mdi) : (120 - mdi);
		MD5Update( PADDING, padLen);
	
		/* append length in bits and transform */
		for (i = 0, ii = 0; i < 14; i++, ii += 4)
			in[i] = (((int)mdContext.in[ii+3]) << 24) |
							(((int)mdContext.in[ii+2]) << 16) |
							(((int)mdContext.in[ii+1]) << 8) |
							((int)mdContext.in[ii]);
		Transform();
	
		/* store buffer in digest */
		for (i = 0, ii = 0; i < 4; i++, ii += 4) {
			mdContext.digest[ii] = (byte)(mdContext.buf[i] & 0xFF);
			mdContext.digest[ii+1] =
				(byte)((mdContext.buf[i] >> 8) & 0xFF);
			mdContext.digest[ii+2] =
				(byte)((mdContext.buf[i] >> 16) & 0xFF);
			mdContext.digest[ii+3] =
				(byte)((mdContext.buf[i] >> 24) & 0xFF);
		}
	}
	
	/* Basic MD5 step. Transforms buf based on in.
	 */
	private void Transform() {
		
		int a = mdContext.buf[0];
		int b = mdContext.buf[1];
		int c = mdContext.buf[2];
		int d = mdContext.buf[3];
	
		/* Round 1 */
		int S11 = 7;
		int S12 = 12;
		int S13 = 17;
		int S14 = 22;
		
		a = FF (a, b, c, d, in[ 0], S11, 0xd76aa478); /* 1 */
		d = FF (d, a, b, c, in[ 1], S12, 0xe8c7b756); /* 2 */
		c = FF (c, d, a, b, in[ 2], S13, 0x242070db); /* 3 */
		b = FF (b, c, d, a, in[ 3], S14, 0xc1bdceee); /* 4 */
		a = FF (a, b, c, d, in[ 4], S11, 0xf57c0faf); /* 5 */
		d = FF (d, a, b, c, in[ 5], S12, 0x4787c62a); /* 6 */
		c = FF (c, d, a, b, in[ 6], S13, 0xa8304613); /* 7 */
		b = FF (b, c, d, a, in[ 7], S14, 0xfd469501); /* 8 */
		a = FF (a, b, c, d, in[ 8], S11, 0x698098d8); /* 9 */
		d = FF (d, a, b, c, in[ 9], S12, 0x8b44f7af); /* 10 */
		c = FF (c, d, a, b, in[10], S13, 0xffff5bb1); /* 11 */
		b = FF (b, c, d, a, in[11], S14, 0x895cd7be); /* 12 */
		a = FF (a, b, c, d, in[12], S11, 0x6b901122); /* 13 */
		d = FF (d, a, b, c, in[13], S12, 0xfd987193); /* 14 */
		c = FF (c, d, a, b, in[14], S13, 0xa679438e); /* 15 */
		b = FF (b, c, d, a, in[15], S14, 0x49b40821); /* 16 */
	
		/* Round 2 */
		int S21 = 5;
		int S22 = 9;
		int S23 = 14;
		int S24 = 20;
		
		a = GG (a, b, c, d, in[ 1], S21, 0xf61e2562); /* 17 */
		d = GG (d, a, b, c, in[ 6], S22, 0xc040b340); /* 18 */
		c = GG (c, d, a, b, in[11], S23, 0x265e5a51); /* 19 */
		b = GG (b, c, d, a, in[ 0], S24, 0xe9b6c7aa); /* 20 */
		a = GG (a, b, c, d, in[ 5], S21, 0xd62f105d); /* 21 */
		d = GG (d, a, b, c, in[10], S22,  0x2441453); /* 22 */
		c = GG (c, d, a, b, in[15], S23, 0xd8a1e681); /* 23 */
		b = GG (b, c, d, a, in[ 4], S24, 0xe7d3fbc8); /* 24 */
		a = GG (a, b, c, d, in[ 9], S21, 0x21e1cde6); /* 25 */
		d = GG (d, a, b, c, in[14], S22, 0xc33707d6); /* 26 */
		c = GG (c, d, a, b, in[ 3], S23, 0xf4d50d87); /* 27 */
		b = GG (b, c, d, a, in[ 8], S24, 0x455a14ed); /* 28 */
		a = GG (a, b, c, d, in[13], S21, 0xa9e3e905); /* 29 */
		d = GG (d, a, b, c, in[ 2], S22, 0xfcefa3f8); /* 30 */
		c = GG (c, d, a, b, in[ 7], S23, 0x676f02d9); /* 31 */
		b = GG (b, c, d, a, in[12], S24, 0x8d2a4c8a); /* 32 */
		
		/* Round 3 */
		int S31 = 4;
		int S32 = 11;
		int S33 = 16;
		int S34 = 23;
		
		a = HH (a, b, c, d, in[ 5], S31, 0xfffa3942); /* 33 */
		d = HH (d, a, b, c, in[ 8], S32, 0x8771f681); /* 34 */
		c = HH (c, d, a, b, in[11], S33, 0x6d9d6122); /* 35 */
		b = HH (b, c, d, a, in[14], S34, 0xfde5380c); /* 36 */
		a = HH (a, b, c, d, in[ 1], S31, 0xa4beea44); /* 37 */
		d = HH (d, a, b, c, in[ 4], S32, 0x4bdecfa9); /* 38 */
		c = HH (c, d, a, b, in[ 7], S33, 0xf6bb4b60); /* 39 */
		b = HH (b, c, d, a, in[10], S34, 0xbebfbc70); /* 40 */
		a = HH (a, b, c, d, in[13], S31, 0x289b7ec6); /* 41 */
		d = HH (d, a, b, c, in[ 0], S32, 0xeaa127fa); /* 42 */
		c = HH (c, d, a, b, in[ 3], S33, 0xd4ef3085); /* 43 */
		b = HH (b, c, d, a, in[ 6], S34,  0x4881d05); /* 44 */
		a = HH (a, b, c, d, in[ 9], S31, 0xd9d4d039); /* 45 */
		d = HH (d, a, b, c, in[12], S32, 0xe6db99e5); /* 46 */
		c = HH (c, d, a, b, in[15], S33, 0x1fa27cf8); /* 47 */
		b = HH (b, c, d, a, in[ 2], S34, 0xc4ac5665); /* 48 */
		
		/* Round 4 */
		int S41 = 6;
		int S42 = 10;
		int S43 = 15;
		int S44 = 21;
		
		a = II (a, b, c, d, in[ 0], S41, 0xf4292244); /* 49 */
		d = II (d, a, b, c, in[ 7], S42, 0x432aff97); /* 50 */
		c = II (c, d, a, b, in[14], S43, 0xab9423a7); /* 51 */
		b = II (b, c, d, a, in[ 5], S44, 0xfc93a039); /* 52 */
		a = II (a, b, c, d, in[12], S41, 0x655b59c3); /* 53 */
		d = II (d, a, b, c, in[ 3], S42, 0x8f0ccc92); /* 54 */
		c = II (c, d, a, b, in[10], S43, 0xffeff47d); /* 55 */
		b = II (b, c, d, a, in[ 1], S44, 0x85845dd1); /* 56 */
		a = II (a, b, c, d, in[ 8], S41, 0x6fa87e4f); /* 57 */
		d = II (d, a, b, c, in[15], S42, 0xfe2ce6e0); /* 58 */
		c = II (c, d, a, b, in[ 6], S43, 0xa3014314); /* 59 */
		b = II (b, c, d, a, in[13], S44, 0x4e0811a1); /* 60 */
		a = II (a, b, c, d, in[ 4], S41, 0xf7537e82); /* 61 */
		d = II (d, a, b, c, in[11], S42, 0xbd3af235); /* 62 */
		c = II (c, d, a, b, in[ 2], S43, 0x2ad7d2bb); /* 63 */
		b = II (b, c, d, a, in[ 9], S44, 0xeb86d391); /* 64 */

		
		mdContext.buf[0] += a;
		mdContext.buf[1] += b;
		mdContext.buf[2] += c;
		mdContext.buf[3] += d;
	}
	
	
	/*******************************/
	/* Swap byte order in a word32 */
	/*******************************/
	
	private void swap_bytes( byte[] CW, int pos ){
		
		byte temp;
	
		temp = CW[ pos ];
		CW[ pos ] = CW[ pos + 3];
		CW[ pos + 3 ] = temp;
		temp = CW[ pos + 1 ];
		CW[ pos + 1 ] = CW[ pos + 2 ];
		CW[ pos + 2 ] = temp;
	}
	
	
	private String MakeMD5HashCode( String sData ) throws RuntimeException {
		
		int iLength = sData.length();
		byte[] pData = null;
		try{
			pData = sData.getBytes("UTF8");
		}catch( UnsupportedEncodingException e ){
			throw new RuntimeException( "UTF8 encoding not supported" );
		}

		byte[] pHash = MD5( pData, iLength);
		//pHash[DIGEST_LENGTH] = 0;
		String sHash = getStringForm(pHash);
		pHash = null;
		pData = null;
		return sHash;
	}

	
	// QM4 server specific interface
	
	
	public static String MakeHashCode( String sData ) {
	
		QMMD5 MD5 = new QMMD5();
	
		return MD5.MakeMD5HashCode( sData );
	}
	
	// make a hash code of the session and question IDs
	public static String MakeItemID( String sSessionID, String sQuestionID, String sItemID ){
		
		String sData = sSessionID + sQuestionID + sItemID;	
		return MakeHashCode( sData );
	}
	
	// make a hash code for continue ID
	public static String MakeContinueID( String sSessionID, String sAccessID, int iSectionID ){
		
		String sData = sSessionID + sAccessID + String.valueOf(iSectionID);
		return MakeHashCode( sData );
	}
	
	// make a hash code of the session and access IDs and the section number
	public static String MakeChecksum( String sSessionID, String sAccessID, int iSection ){
		
		String sData = sSessionID + sAccessID + String.valueOf(iSection);
		return MakeHashCode( sData );
	}
	
	// make checksum to accompany request for end of session report
	public static String MakeReportChecksum( String sResultIndex, String sName ){
		
		String sData = sResultIndex + sName ;
		return MakeHashCode( sData );
	}
	
	private static String getStringForm( byte buf[] ) {
		StringBuffer sb = new StringBuffer(2*buf.length) ;
		for (int i = 0 ; i < buf.length; i++) {
		    int h = (buf[i] & 0xf0) >> 4 ;
		    int l = (buf[i] & 0x0f) ;
		    sb.append (new Character((char)((h>9) ? 'a'+h-10 : '0'+h))) ;
		    sb.append (new Character((char)((l>9) ? 'a'+l-10 : '0'+l))) ;
		}
		return sb.toString() ;
    }
}