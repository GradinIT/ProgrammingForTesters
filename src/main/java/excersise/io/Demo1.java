package excersise.io;

import	java.awt.*;
import	javax.swing.*;
import	java.awt.event.*;

public class	Demo1	extends	JFrame	{
    public	Demo1()	{
        JPanel	main	=	new	JPanel();
        main.setBackground(Color.red);
        main.setLayout(new	BorderLayout());
        JPanel	pN	=	new	JPanel();
        pN.setBackground(Color.blue);
        main.add(pN,BorderLayout.NORTH);
        JLabel	l	=	new	JLabel("56");
        pN.add(l);
        JPanel	pC	=	new	JPanel();
        pC.setBackground(Color.yellow);
        pC.setLayout(new	GridLayout(4,4));
        main.add(pC,BorderLayout.CENTER);
        JButton	b;
        b	=	new	JButton("1");
        pC.add(b);
        b	=	new	JButton("2");
        pC.add(b);
        b	=	new	JButton("3");
        pC.add(b);
        b	=	new	JButton("+");
        pC.add(b);
        b	=	new	JButton("4");
        pC.add(b);
        b	=	new	JButton("5");
        pC.add(b);
        b	=	new	JButton("6");
        pC.add(b);
        b	=	new	JButton("-");
        pC.add(b);
        b	=	new	JButton("7");
        pC.add(b);
        b	=	new	JButton("8");
        pC.add(b);
        b	=	new	JButton("9");
        pC.add(b);
        b	=	new	JButton("*");
        pC.add(b);
        b	=	new	JButton("0");
        pC.add(b);
        b	=	new	JButton("C");
        pC.add(b);
        b	=	new	JButton("=");
        pC.add(b);
        b	=	new	JButton("/");
        pC.add(b);
        add(main);
        pack();
        setVisible(true);
        this.addComponentListener(new	ComponentListener()	{
            public	void	componentHidden(ComponentEvent	e)	{}
            public	void	componentMoved(ComponentEvent	e)	{}
            public	void	componentResized(ComponentEvent	e)	{
                pN.setPreferredSize(
                        new	Dimension(main.getWidth(),main.getHeight()/3));
            }
            public	void	componentShown(ComponentEvent	e)	{}
        });
    }
    public	static	void	main(String[]	args)	{
        Demo1	f	=	new	Demo1();
    }
}