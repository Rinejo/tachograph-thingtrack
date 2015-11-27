/**
 * 
 */
package org.tacografo.file.vublock;

import java.util.ArrayList;
import java.util.Arrays;

import org.tacografo.tiposdatos.OctetString;
import org.tacografo.file.Block;
import org.tacografo.file.vublock.subblock.VuDetailedSpeedBlock;
import org.tacografo.tiposdatos.Number;

/**
 * @author Andres Carmona Gil
 *@version 0.0.1
 */
public class Speed extends Block{
	
	/**
	 * 2.128.   VuDetailedSpeedData
	 * Información pormenorizada almacenada en una unidad intravehicular y relativa a la velocidad del vehículo.
	 * VuDetailedSpeedData ::= SEQUENCE
	 * noOfSpeedBlocks INTEGER(0.216-1),
	 * vuDetailedSpeedBlocks SET SIZE(noOfSpeedBlocks) OF VuDetailedSpeedBlock
	 * }
	 * noOfSpeedBlocks es el número de bloques con datos de velocidad que hay en el conjunto vuDetailedSpeedBlocks.
	 * vuDetailedSpeedBlocks es el conjunto de bloques con datos pormenorizados sobre la velocidad.
	 */
	private ArrayList<VuDetailedSpeedBlock> vuDetailedData;
	private int noOfSpeedBlocks;
	private String signature;
	private int size;
	
	
	public Speed(byte[] bytes) throws Exception{
		int start=0;
		this.noOfSpeedBlocks=Number.getNumber(Arrays.copyOfRange(bytes, start, start+=Sizes.NOOFSPEEDBLOCKS.getSize()));
		
		if(this.noOfSpeedBlocks>0)
		this.setVuDetailedData(Arrays.copyOfRange(bytes, start, start+=Sizes.VUDETAILEDSPEEDBLOCK.getSize()*this.noOfSpeedBlocks));
		this.signature=OctetString.getHexString(Arrays.copyOfRange(bytes, start, start+=Sizes.SIGNATURE_TREP1.getSize()));
		this.size=start;	
		
	}


	private void setVuDetailedData(byte[] bytes) {
		this.vuDetailedData=new ArrayList();
		int end=bytes.length/Sizes.VUDETAILEDSPEEDBLOCK.getSize();		
		int start=0;
		VuDetailedSpeedBlock vdsb;
		for (int i=0;i<this.noOfSpeedBlocks;i++){			
			vdsb=new VuDetailedSpeedBlock(Arrays.copyOfRange(bytes, start, start+=Sizes.VUDETAILEDSPEEDBLOCK.getSize()));			
			this.vuDetailedData.add(vdsb);
		}
		
	}


	/**
	 * @return the vuDetailedData
	 */
	public ArrayList<VuDetailedSpeedBlock> getVuDetailedData() {
		return vuDetailedData;
	}


	/**
	 * @param vuDetailedData the vuDetailedData to set
	 */
	public void setVuDetailedData(ArrayList<VuDetailedSpeedBlock> vuDetailedData) {
		this.vuDetailedData = vuDetailedData;
	}


	/**
	 * @return the noOfSpeedBlocks
	 */
	public int getNoOfSpeedBlocks() {
		return noOfSpeedBlocks;
	}


	/**
	 * @param noOfSpeedBlocks the noOfSpeedBlocks to set
	 */
	public void setNoOfSpeedBlocks(int noOfSpeedBlocks) {
		this.noOfSpeedBlocks = noOfSpeedBlocks;
	}


	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}


	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}


	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}


	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Speed [vuDetailedData=" + vuDetailedData + ", noOfSpeedBlocks=" + noOfSpeedBlocks + ", signature="
				+ signature + ", size=" + size + "]";
	}
	

}
